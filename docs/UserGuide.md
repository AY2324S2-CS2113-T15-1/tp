# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

[//]: # (## Features )

## General

### Viewing help: `help`
Shows a help message listing the commands available in the application.

**Format:** 
`help`

### Exiting the program: `bye`

Exits the program.

**Format:** 
`bye`

## Calories Tracker

### Input calorie intake: `calories in`
Adds a calorie gaining activity into the calories tracker.

**Format:** 
`calories in DESCRIPTION c/CALORIES_IN d/DATE`

* The calories must be a positive integer 1, 2, 3, …, measured in kcal. 
* The time indicated should follow the 24-hour system. 
* The date provided should be of the form YYYY-MM-DD.

**Examples:** 
* `calories in eat chicken rice c/678 d/2022-02-24`
* `calories in drink liho milk tea c/430 d/1022-03-25`


### Input calorie loss: `calories out`
Adds a calorie burning activity into the calories tracker.

**Format:** 
`calories out DESCRIPTION c/CALORIES_OUT d/DATE`

* The calories must be a positive integer 1, 2, 3, …, measured in kcal. 
* The time indicated should follow the 24-hour system.
* The date provided should be of the form YYYY-MM-DD.

**Examples:**

* `calories out Run around NUS c/678 d/2022-02-24` 
* `calories out Walk to i3 building c/67 d/2022-03-25`

### Listing calorie items: `calories list`
Shows a list of all activities in the calories tracker.

**Format:**
`calories list`

### Deleting a calorie item: `calories delete`
Deletes the specified activity from the calories tracker.

**Format:**
`calories delete INDEX`

**Examples:**

* `calories list` followed by `calories delete 2` deletes the 2nd activity in the calories tracker.

## Hydration Tracker

### Input hydration intake: `hydration in`
Adds a hydration record into the hydration tracker.

**Format:**
`hydration in DESCRIPTION v/VOLUME d/DATE`

* The volume must be a positive integer 1, 2, 3, …, measured in milliliters.
* The time indicated should follow the 24-hour system.
* The date provided should be of the form YYYY-MM-DD.

**Examples:**
* `hydration in Milo v/1000 d/2022-03-25`
* `hydration in Tea v/200 d/2022-02-05`

### Listing hydration items: `hydration list`
Show the list of all hydration records in the hydration tracker.

**Format:**
`hydration list`

### Deleting a hydration item: `hydration delete`
Deletes the hydration record according to the index.

**Format:**
`hydration delete INDEX`

Delete the drinking water record at the specific index. The index refers to the index number shown in the displayed Hydration list. The index must be a positive integer 1, 2, 3, …​

**Examples:**
* `hydration list` followed by `hydration delete 2` deletes the 2nd hydration record from the hydration tracker.

## Sleep Tracker

### Input sleeping hours: `sleep add`
Adds a sleep record into the sleep tracker.

**Format:**
`sleep add DURATION d/DATE`
* The duration provided must be a positive real number.
* The time indicated should follow the 24-hour system.
* The date provided should be of the form YYYY-MM-DD.

**Notes about the command format:**
Including the DATE is optional. However, the DURATION must be included!

**Examples:**
* `sleep add 7.5 d/110324`
* `sleep add 8`

### Listing sleep records: `sleep list`
Show the list of all sleep records in the sleep tracker.

**Format:**
`sleep list`

### Deleting a sleep record: `sleep delete`
Deletes the sleep record according to the index.

**Format:**
`sleep delete INDEX`
* Delete the sleep record at the specific index.
* The index refers to the index number shown in the displayed sleeping records list.
* The index must be a positive integer 1, 2, 3, …​

**Examples:**
* `list` followed by `sleep delete 2` deletes the 2nd sleep record from the sleep tracker.


### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
