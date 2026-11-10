param([string]$GeneratorJar = '')
$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
if (-not $GeneratorJar) {
    $GeneratorJar = Join-Path $projectRoot '.tools/openapi-generator-cli-7.12.0.jar'
}
$GeneratorJar = (Resolve-Path -LiteralPath $GeneratorJar).Path
$version = & java -jar $GeneratorJar version
if ($LASTEXITCODE -ne 0 -or $version.Trim() -ne '7.12.0') {
    throw 'Expected OpenAPI Generator CLI 7.12.0'
}
Push-Location $projectRoot
try {
    & java -jar $GeneratorJar validate -i api.yaml
    if ($LASTEXITCODE -ne 0) { throw 'OpenAPI validation failed' }

    # Remove only the previously generated API/DTO files, including renamed models.
    # Other application code and configuration are never included in this list.
    $serviceRoot = (Resolve-Path -LiteralPath 'service').Path
    $manifest = Join-Path $serviceRoot '.openapi-generator/FILES'
    if (Test-Path -LiteralPath $manifest) {
        foreach ($entry in Get-Content -LiteralPath $manifest) {
            if ($entry -eq 'src/main/java/ru/cinema/api/ApiUtil.java') { continue }
            if ($entry -notmatch '^src/main/java/ru/cinema/(api|dto)/[A-Za-z0-9]+\.java$') { continue }
            $generatedPath = [IO.Path]::GetFullPath((Join-Path $serviceRoot $entry))
            if (-not $generatedPath.StartsWith($serviceRoot + [IO.Path]::DirectorySeparatorChar, [StringComparison]::OrdinalIgnoreCase)) {
                throw 'Generated file is outside the service directory'
            }
            if (Test-Path -LiteralPath $generatedPath) {
                if ((Get-Content -Raw -LiteralPath $generatedPath) -notmatch 'org.openapitools.codegen.languages.SpringCodegen') {
                    throw "Refusing to replace a file without the generator marker: $entry"
                }
                Remove-Item -LiteralPath $generatedPath
            }
        }
    }
    & java -jar $GeneratorJar generate -i api.yaml -g spring -o service -c generator-config.yaml
    if ($LASTEXITCODE -ne 0) { throw 'Code generation failed' }
} finally {
    Pop-Location
}
