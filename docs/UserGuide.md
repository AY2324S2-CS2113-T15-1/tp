# User Guide

## Introduction

LifeTrack is a desktop app for students to track their health data, optimized for use via a Command Line Interface (CLI). It tracks calories, hydration and sleep data for the user, while also providing daily recommendations for calorie and hydration intake, based on the user's build and gender, as well as their body goals and activity levels.

## Quick links
- [Quick Start](#quick-start)
- [General](#general)
  - [help](#viewing-help-help)
  - [bye](#exiting-the-program-bye)
- [Calories Tracker](#calories-tracker)
  - [Input calories Intake](#input-calorie-intake-calories-in)
  - [Input calorie loss](#input-calorie-loss-calories-out)
  - [Listing calorie items](#listing-calorie-items-calories-list)
  - [Deleting a calorie item](#deleting-a-calorie-item-calories-delete)
- [Hydration Tracker](#hydration-tracker)
  - [Input hydration intake](#input-hydration-intake-hydration-in)
  - [Listing hydration items](#listing-hydration-items-hydration-list)
  - [Deleting a hydration item](#deleting-a-hydration-item-hydration-delete)
- [Sleep Tracker](#sleep-tracker)
  - [Input sleeping hours](#input-sleeping-hours-sleep-add)
  - [Listing sleep records](#listing-sleep-records-sleep-list)
  - [Deleting a sleep record](#deleting-a-sleep-record-sleep-delete)
- [FAQ](#faq)
- [Command Summary](#command-summary)

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

#### Expected Output

         -----------------------------------------------------------------------------
         LifeTrack Command List:
         - help: Displays a list of available commands and their descriptions.
         -----------------------------------------------------------------------------
         - calories in <food> c/<calories> d/<date, format:YYYY-MM-DD> m/[carbohydrates, proteins, fats]: Adds a calorie gaining entry into the calories tracker.
         - calories out <activity> c/<calories> d/<date, format:YYYY-MM-DD>: Adds a calorie burning entry into the calories tracker.
         - calories list: Displays all entries currently stored in the calorie list.
         - calories delete <index>: Deletes the entry at the specified index from the calorie list.
         -----------------------------------------------------------------------------
         - hydration in <type of beverage> v/<volume> d/<date, format:YYYY-MM-DD>: Adds a hydration entry into the hydration tracker.
         - hydration list: Displays all entries currently stored in the hydration list.
         - hydration delete <index>: Deletes the hydration entry at the specified index from the hydration list.     
         -----------------------------------------------------------------------------
         - sleep add <duration> d/<date, format:YYYY-MM-DD>: Adds a sleep entry into the sleep tracker.
         - sleep list: Displays all entries currently stored in the sleep list.
         - sleep delete <index>: Deletes the entry at the specified index from the sleep list.
         -----------------------------------------------------------------------------
         - user setup <name> h/<height> w/<weight> a/<age> s/<sex> e/<exercise_level> g/<body_goal>: Create a new user, or edit an existing one.
         - user progress: Display calories and hydration progress towards the daily requirement.
         -----------------------------------------------------------------------------

### Exiting the program: `bye`

Exits the program.

**Format:** 
`bye`

#### Expected Output

         -----------------------------------------------------------------------------
         Bye! See you again soon ^^

## Calories Tracker

### Input calorie intake: `calories in`
Adds a calorie gaining activity into the calories tracker.
Macronutrients such as Carbohydrates, Proteins and Fats can be included if needed.

**Format:** 
`calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]`

* The `DESCRIPTION` refers to the food that the person consumed.
* The `CALORIES` must be a positive integer 1, 2, 3, …, measured in kcal. 
* The `DATE` provided should be of the form YYYY-MM-DD, such as 2024-03-04.
* Macronutrients field including `CARBOHYDRATES`, `PROTEINS` and `FATS` is optional. The macronutrients must be a positive integer 1, 2, 3, measured in grams.

**Examples:** 
* `calories in chicken rice c/678 d/2022-02-24`
* `calories in hamburger c/983 d/2024-04-03`
* `calories in cai png c/543 d/2024-04-13 m/200, 150, 100`
* `calories in drink liho milk tea c/200 d/2024-04-25 m/50, 20, 10`


### Input calorie loss: `calories out`
Adds a calorie burning activity into the calories tracker.

**Format:** 
`calories out DESCRIPTION c/CALORIES d/DATE`

* The `DESCRIPTION` refers to any activity that resulted in loss of calories.
* The `CALORIES` must be a positive integer 1, 2, 3, …, measured in kcal. 
* The `DATE` provided should be of the form YYYY-MM-DD such as 2024-04-03.

**Examples:**

* `calories out Run around NUS c/678 d/2022-02-24` 
* `calories out Walk to i3 building c/67 d/2022-03-25`
* `calories out go gym c/300 d/2024-04-03`

### Listing calorie items: `calories list`
Shows a list of all activities in the calories tracker. Includes both calories in and out.

**Format:**
`calories list`

#### Sample output
         -----------------------------------------------------------------------------
	 Your Caloric List:
	 1. 	 Date: 2024-06-15, Description: chicken, Calories: 1000
	 2. 	 Date: 2024-06-15, Description: chicken, Calories: 1000
	 3. 	 Date: 2024-05-15, Description: chicken, Calories: 1000
	 4. 	 Date: 2023-03-01, Description: taco, Calories: 1
	 5. 	 Date: 2024-04-03, Description: burger, Calories: 100
	 6. 	 Date: 2024-04-03, Description: cai png, Calories: 1000
	 7. 	 Date: 2024-04-03, Description: cai png, Calories: 500
	 8. 	 Date: 2024-04-13, Description: liho milk tea, Calories: 200
         -----------------------------------------------------------------------------

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

| Action                 | Format, Examples                                                           |
|------------------------|----------------------------------------------------------------------------|
| Help                   | `help`                                                                     |
| Add calories intake    | `calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]` |
| Add calories outflow   | `calories out DESCRIPTION c/CALORIES d/DATE`                               |
| List calories          | `calories list`                                                            |
| Delete calories entry  | `calories delete INDEX`                                                    |
| Add hydration intake   | `hydration in DESCRIPTION v/VOLUME d/DATE`                                 |
| List hydration         | `hydration list`                                                           |
| Delete hydration entry | `hydration delete INDEX`                                                   |
| Add sleep              | `sleep add DURATION d/DATE`                                                |
| List sleep             | `sleep list`                                                               |
| Delete sleep entry     | `sleep delete INDEX`                                                       |
* Add todo `todo n/TODO_NAME d/DEADLINE`
