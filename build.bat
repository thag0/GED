@echo off
setlocal enabledelayedexpansion

set SRC_DIR=ged
set BIN_DIR=bin
set JAVA_FILES=

if exist "%BIN_DIR%" rmdir /S /Q "%BIN_DIR%"
mkdir "%BIN_DIR%"

echo Coletando arquivos
for /R "%SRC_DIR%" %%f in (*.java) do (
    set JAVA_FILES=!JAVA_FILES! "%%f"
)

javac -g -parameters -d "%BIN_DIR%" -sourcepath "%SRC_DIR%" %JAVA_FILES%

if %errorlevel% neq 0 (
    echo Erro durante a compilação.
    exit /b %errorlevel%
)

jar cvf "%BIN_DIR%\ged.jar" -C "%BIN_DIR%" .

echo.
echo Jar gerado em "%BIN_DIR%"

echo Build finalizado.