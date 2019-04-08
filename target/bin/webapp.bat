@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\8.5.23\tomcat-embed-core-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\8.5.23\tomcat-annotations-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.23\tomcat-embed-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\8.5.23\tomcat-embed-el-8.5.23.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper\8.5.23\tomcat-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-servlet-api\8.5.23\tomcat-servlet-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-juli\8.5.23\tomcat-juli-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-el-api\8.5.23\tomcat-el-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-api\8.5.23\tomcat-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util-scan\8.5.23\tomcat-util-scan-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util\8.5.23\tomcat-util-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper-el\8.5.23\tomcat-jasper-el-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jsp-api\8.5.23\tomcat-jsp-api-8.5.23.jar;"%REPO%"\javazoom\jlayer\1.0.1\jlayer-1.0.1.jar;"%REPO%"\org\glassfish\javax.faces\2.2.10\javax.faces-2.2.10.jar;"%REPO%"\javax\servlet\javax.servlet-api\4.0.0\javax.servlet-api-4.0.0.jar;"%REPO%"\com\googlecode\json-simple\json-simple\1.1.1\json-simple-1.1.1.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\org\apache\maven\plugins\maven-compiler-plugin\3.7.0\maven-compiler-plugin-3.7.0.jar;"%REPO%"\org\apache\maven\maven-plugin-api\3.0\maven-plugin-api-3.0.jar;"%REPO%"\org\apache\maven\maven-model\3.0\maven-model-3.0.jar;"%REPO%"\org\sonatype\sisu\sisu-inject-plexus\1.4.2\sisu-inject-plexus-1.4.2.jar;"%REPO%"\org\sonatype\sisu\sisu-inject-bean\1.4.2\sisu-inject-bean-1.4.2.jar;"%REPO%"\org\sonatype\sisu\sisu-guice\2.1.7\sisu-guice-2.1.7-noaop.jar;"%REPO%"\org\apache\maven\maven-artifact\3.0\maven-artifact-3.0.jar;"%REPO%"\org\codehaus\plexus\plexus-utils\2.0.4\plexus-utils-2.0.4.jar;"%REPO%"\org\apache\maven\maven-core\3.0\maven-core-3.0.jar;"%REPO%"\org\apache\maven\maven-settings\3.0\maven-settings-3.0.jar;"%REPO%"\org\apache\maven\maven-settings-builder\3.0\maven-settings-builder-3.0.jar;"%REPO%"\org\apache\maven\maven-repository-metadata\3.0\maven-repository-metadata-3.0.jar;"%REPO%"\org\apache\maven\maven-model-builder\3.0\maven-model-builder-3.0.jar;"%REPO%"\org\apache\maven\maven-aether-provider\3.0\maven-aether-provider-3.0.jar;"%REPO%"\org\sonatype\aether\aether-impl\1.7\aether-impl-1.7.jar;"%REPO%"\org\sonatype\aether\aether-spi\1.7\aether-spi-1.7.jar;"%REPO%"\org\sonatype\aether\aether-api\1.7\aether-api-1.7.jar;"%REPO%"\org\sonatype\aether\aether-util\1.7\aether-util-1.7.jar;"%REPO%"\org\codehaus\plexus\plexus-interpolation\1.14\plexus-interpolation-1.14.jar;"%REPO%"\org\codehaus\plexus\plexus-classworlds\2.2.3\plexus-classworlds-2.2.3.jar;"%REPO%"\org\codehaus\plexus\plexus-component-annotations\1.5.5\plexus-component-annotations-1.5.5.jar;"%REPO%"\org\sonatype\plexus\plexus-sec-dispatcher\1.3\plexus-sec-dispatcher-1.3.jar;"%REPO%"\org\sonatype\plexus\plexus-cipher\1.4\plexus-cipher-1.4.jar;"%REPO%"\org\apache\maven\shared\maven-shared-utils\3.1.0\maven-shared-utils-3.1.0.jar;"%REPO%"\org\apache\maven\shared\maven-shared-incremental\1.1\maven-shared-incremental-1.1.jar;"%REPO%"\org\codehaus\plexus\plexus-java\0.9.2\plexus-java-0.9.2.jar;"%REPO%"\org\ow2\asm\asm\6.0_BETA\asm-6.0_BETA.jar;"%REPO%"\com\thoughtworks\qdox\qdox\2.0-M7\qdox-2.0-M7.jar;"%REPO%"\org\codehaus\plexus\plexus-compiler-api\2.8.2\plexus-compiler-api-2.8.2.jar;"%REPO%"\org\codehaus\plexus\plexus-compiler-manager\2.8.2\plexus-compiler-manager-2.8.2.jar;"%REPO%"\org\codehaus\plexus\plexus-compiler-javac\2.8.2\plexus-compiler-javac-2.8.2.jar;"%REPO%"\org\springframework\integration\spring-integration-file\5.1.3.RELEASE\spring-integration-file-5.1.3.RELEASE.jar;"%REPO%"\org\springframework\integration\spring-integration-core\5.1.3.RELEASE\spring-integration-core-5.1.3.RELEASE.jar;"%REPO%"\org\springframework\spring-core\5.1.5.RELEASE\spring-core-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-jcl\5.1.5.RELEASE\spring-jcl-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-aop\5.1.5.RELEASE\spring-aop-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-beans\5.1.5.RELEASE\spring-beans-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-context\5.1.5.RELEASE\spring-context-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-expression\5.1.5.RELEASE\spring-expression-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-messaging\5.1.5.RELEASE\spring-messaging-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-tx\5.1.5.RELEASE\spring-tx-5.1.5.RELEASE.jar;"%REPO%"\org\springframework\retry\spring-retry\1.2.4.RELEASE\spring-retry-1.2.4.RELEASE.jar;"%REPO%"\io\projectreactor\reactor-core\3.2.6.RELEASE\reactor-core-3.2.6.RELEASE.jar;"%REPO%"\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;"%REPO%"\commons-io\commons-io\2.6\commons-io-2.6.jar;"%REPO%"\Fowlart1988\task05_Lambdas_Streams\1.0-SNAPSHOT\task05_Lambdas_Streams-1.0-SNAPSHOT.war

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" launch.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
