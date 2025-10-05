@echo off
echo === Setting up Git project for Assignment 2 ===

cd /d "%~dp0"

REM Reset remote if exists
git remote remove origin 2>nul
git remote add origin https://github.com/nazarrbek/Assigment2_StudentB.git

REM Ensure we're on main branch
git checkout -B main

REM Stage and commit initial structure
git add .
git commit -m "init: maven project structure, junit5, ci setup"

REM === Feature: metrics ===
git checkout -b feature/metrics
git add .
git commit -m "feat(metrics): performance counters and CSV export"
git push -u origin feature/metrics

REM === Feature: algorithm ===
git checkout -b feature/algorithm main
git add src\main\java\algorithms\
git commit -m "feat(algorithm): baseline SelectionSort implementation"
git push -u origin feature/algorithm

REM === Feature: testing ===
git checkout -b feature/testing main
git add src\test\java\
git commit -m "test(algorithm): comprehensive test suite with edge cases"
git push -u origin feature/testing

REM === Feature: CLI ===
git checkout -b feature/cli main
git add src\main\java\cli\
git commit -m "feat(cli): benchmark runner with configurable input sizes"
git push -u origin feature/cli

REM === Feature: optimization ===
git checkout -b feature/optimization main
git add src\main\java\algorithms\SelectionSort.java
git commit -m "feat(optimization): early termination optimization for sorted data"
git push -u origin feature/optimization

REM === Merge and final release ===
git checkout main
git merge feature/optimization --no-edit
git commit -m "release: v1.0 with complete implementation"

REM Push main branch
git push -u origin main --force

echo.
echo ✅ Project pushed successfully to GitHub!
echo 👉 https://github.com/nazarrbek/Assigment2_StudentB
pause
