@echo off

set CT=%~1

mkdir "./features/"
mkdir "./features/steps/"
echo Coding.... > "./features/steps/%CT%_steps.py"
echo Feature: %CT% > "./features/%CT%.feature"

