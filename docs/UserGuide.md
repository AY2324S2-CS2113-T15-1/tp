# LongAh! User Guide

## Introduction

LongAh! is a CLI-based application designed to help users track debts within friend groups and determine the 
least transaction method of settling these debts. It is optimized for busy people with large transaction quantities 
among friends.

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of `LongAh!` from [here](https://github.com/AY2324S2-CS2113-T15-1/tp/releases).
3. Copy the JAR file to the folder you want to use as the home folder for your LongAh! application.
4. Open a command terminal, navigate to the folder containing the JAR file and run the command:
```
java -jar tp.jar
```
5. Upon starting the application, you will be prompted to enter your PIN. The user PIN is required to access the application.
The app will prompt you to create your own PIN if it is your first time using the application.
6. You can now start using LongAh! by entering commands into the command terminal.

<div style="page-break-after: always;"></div>

## Command Reference

A quick reference table for all commands is presented below. Certain commands have shortcuts which can be used in place of the provided long form commands as well.

| Task                   | Command Expression                                                                                    | Command Shortcut  |
|------------------------|-------------------------------------------------------------------------------------------------------|-------------------|
| Help menu              | `help`                                                                                                | `?`               |
| Add member             | `add member [name]`                                                                                   | `addm` or `am`    |
| Add transaction        | `add transaction [lender] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...`                      | `addt` or `at`    |
| Add dated transaction  | `add transaction lender t/[DD-MM-YYYY HHMM] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...`    | `addt` or `at`    |
| Add group              | `add group [name]`                                                                                    | `addg` or `ag`    |
| List members           | `list members`                                                                                        | `listm` or `lm`   |
| List transactions      | `list transactions`                                                                                   | `listt` or `lt`   |
| List debts             | `list debts`                                                                                          | `listd` or `ld`   |
| List groups            | `list groups`                                                                                         | `listg` or `lg`   |
| Find transactions      | `find transactions [member]`                                                                          | `findt` or `ft`   |
| Find lender            | `find lender [member]`                                                                                | `findl` or `fl`   |
| Find borrower          | `find borrower [member]`                                                                              | `findb` or `fb`   |
| Find debts             | `find debts [member]`                                                                                 | `findd` or `fd`   |
| Delete member          | `delete member [member]`                                                                              | `deletem` or `dm` |
| Delete transaction     | `delete transaction [transaction_index]`                                                              | `deletet` or `dt` |
| Delete group           | `delete group [name]`                                                                                 | `deleteg` or `dg` |
| Edit member            | `edit member [old_name] p/[new_name]`                                                                 | `editm` or `em`   |
| Edit transaction       | `edit transaction [transaction_index] [lender] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...` | `editt` or `et`   |
| Enable PIN             | `pin enable`                                                                                          | N/A               |
| Disable PIN            | `pin disable`                                                                                         | N/A               |
| Reset PIN              | `pin reset`                                                                                           | N/A               |
| Clear all transactions | `clear`                                                                                               | N/A               |
| Settle up debts        | `settleup [member]`                                                                                   | `settle`          |
| Switch groups          | `group [group_name]`                                                                                  | N/A               |
| Filter transactions    | `filter time a/[TIME] b/[TIME]`                                                                       | N/A               |
| View chart             | `chart`                                                                                               | N/A               |
| Exit                   | `exit`                                                                                                | N/A               |

## Table of Contents
- [LongAh! User Guide](#longah-user-guide)
  - [Introduction](#introduction)
  - [Quick Start](#quick-start)
  - [Command Reference](#command-reference)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
    - [Group Management](#group-management)
    - [Member and Transaction Management](#member-and-transaction-management)
    - [Group Balances \& Expense Tracking](#group-balances--expense-tracking)
    - [Debt Simplification](#debt-simplification)
    - [Security](#security)
    - [Data Storage](#data-storage)
    - [Data Editing](#data-editing)
    - [Chart Visualization](#chart-visualization)
  - [Command Format](#command-format)
    - [Viewing help: `help`](#viewing-help-help)
    - [Adding a member: `add member`](#adding-a-member-add-member)
    - [Adding a transaction: `add transaction`](#adding-a-transaction-add-transaction)
    - [Adding a new group `add group`](#adding-a-new-group-add-group)
    - [Listing all members: `list members`](#listing-all-members-list-members)
    - [Listing all transactions: `list transactions`](#listing-all-transactions-list-transactions)
    - [Listing all debts: `list debts`](#listing-all-debts-list-debts)
    - [Listing all groups: `list groups`](#listing-all-groups-list-groups)
    - [Find Transactions: `find transactions`](#find-transactions-find-transactions)
    - [Find Lender `find lender`](#find-lender-find-lender)
    - [Find Borrower `find borrower`](#find-borrower-find-borrower)
    - [Find Debts `find debts`](#find-debts-find-debts)
    - [Deleting a member: `delete member`](#deleting-a-member-delete-member)
    - [Deleting a transaction: `delete transaction`](#deleting-a-transaction-delete-transaction)
    - [Deleting a group `delete group`](#deleting-a-group-delete-group)
    - [Editing a member: `edit member`](#editing-a-member-edit-member)
    - [Editing a transaction: `edit transaction`](#editing-a-transaction-edit-transaction)
    - [Enabling the user PIN: `pin enable`](#enabling-the-user-pin-pin-enable)
    - [Disabling the user PIN: `pin disable`](#disabling-the-user-pin-pin-disable)
    - [Resetting user PIN: `pin reset`](#resetting-user-pin-pin-reset)
    - [Clearing all transactions `clear`](#clearing-all-transactions-clear)
    - [Settle a user's debts: `settleup`](#settle-a-users-debts-settleup)
    - [Switching groups: `group`](#switching-groups-group)
    - [Filter transactions: `filter`](#filter-transactions-filter)
    - [Views the balances of all members on a chart: `chart`](#views-the-balances-of-all-members-on-a-chart-chart)
    - [Exiting the application: `exit`](#exiting-the-application-exit)
  - [FAQ](#faq)
  - [Known Issues](#known-issues)
  - [Future Improvements](#future-improvements)

<div style="page-break-after: always;"></div>

## Features

LongAh! offers robust group management capabilities, allowing users to effortlessly create and delete groups to categorize and organize their expenses. With the ability to seamlessly switch between groups, users can conveniently monitor the financial activities of various social circles or projects.

### Group Management

Within each group, LongAh! provides comprehensive member and transaction management functionalities. Users can easily add, remove, or modify group members and transactions, ensuring accurate and up-to-date records of all financial engagements.

### Member and Transaction Management

Within each group, LongAh! provides comprehensive member and transaction management functionalities. Users can easily add, remove, or modify group members and transactions, ensuring accurate and up-to-date records of all financial engagements.

### Group Balances & Expense Tracking

Tracking group balances and expenses has never been easier with LongAh! Users can log transactions between members, facilitating transparent and equitable expense distribution. LongAh! also offers intuitive visualizations, allowing users to quickly assess group financial dynamics at a glance.

### Debt Simplification

LongAh! streamlines debt settlement processes by automatically computing the optimal repayment strategy. By presenting users with a simplified list of debts and transactions, LongAh! minimizes the effort required to settle financial obligations within the group, fostering smoother financial interactions.

### Security

Protecting sensitive financial data is paramount, which is why LongAh! prioritizes security. With the option to set a personalized PIN, users can safeguard their LongAh! accounts against unauthorized access. This additional layer of security ensures peace of mind, knowing that financial information remains confidential and secure. Additionally, users have the flexibility to enable, disable, or modify their PIN settings according to their preferences and needs.

<div style="page-break-after: always;"></div>

### Data Storage
LongAh! data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
The file is also created automatically if it does not exist.

If all is well, LongAh will save the files in the following data structure during execution.
```
<Your created directory>
│
├─data
│  │  groupList.txt
│  │  pin.txt
│  │
│  ├─<group 1 name>
│  │      members.txt
│  │      transactions.txt
│  │
│  ├─<group 2 name>
│  │      members.txt
│  │      transactions.txt
│   .
│   .
│   .
│
├─log
│      LongAh.log
│
└─tp.jar
```

Note: It is not recommended to edit any data files manually. Corrupt lines of data will be ignored and overwritten over the course of the use of the application.

### Data Editing

LongAh! data is saved as a TXT file in the hard disk. Advanced users are welcome to edit the data file directly, but please ensure that the data is in the correct format.
The PIN TXT file contains the pin hash of each user's PIN for security purposes. It is not recommended to edit this file directly.

### Chart Visualization
The XChart feature in LongAh! provides a visual representation of the balances of all members in the current group. 

<div style="page-break-after: always;"></div>

## Command Format

A command has the general structure:
```
[COMMAND] [SUBCOMMAND] [EXPRESSION]
```

There are 5 main group of commands: 'add', 'delete', 'edit', 'find', 'list', along with other commands.
Command shortcuts are available for certain commands and are detailed below in the "format" section for relevant commands.

### Viewing help: `help`

Shows a help message containing all the commands available in LongAh!.

Format: `help` OR `?`

Example of usage:
```
help
?
    Here are the full list of commands available:

    ADD commands: 
    ____________________________________________________________
    1. `add member [NAME]` - Add a new member to the group.
    ...
    ...
```

### Adding a member: `add member`

Adds a new member to the list of members in LongAh!.

Format: `add member [NAME]` OR `addm` OR `am`

* Name of new member should not be a duplicate of an existing member.
* The entered name should only contain alphanumeric characters, no spaces or special characters are allowed.
* We suggest using pascal case for names with spaces or special characters, i.e. Tan Xiao Hong, Alicia = `TanXiaoHongAlicia`.
* The name of the member is case-sensitive. i.e. 'Alice' and 'alice' are not considered the same member.
* Names are limited to 50 characters.

Example of usage:
```
add member Alice
addm Bob
am Charlie
    Added member: Charlie
```

### Adding a transaction: `add transaction`

Adds a new transaction to the list of transactions in LongAh!.

Format: `add transaction [LENDER] t/[DATE IN DD-MM-YYYY HHMM] p/[BORROWER1] a/[AMOUNT] p/[BORROWER2] a/[AMOUNT] ...` OR `addt` OR `at`
* The transaction supports 1 or more borrower(s), each with custom borrowed amounts.
* `t/` is the prefix for the transaction time, and should be followed by the transaction lender and before the name of the first borrower.
* The transaction time is optional and can be omitted.
* `p/` is the prefix for the borrower's name, and should be followed by the name of the borrower.
* `a/` is the prefix for the amount borrowed, and should be followed by the amount borrowed by that borrower from the lender.
* The `LENDER` and `BORROWER(s)` should be an existing member.
* The `LENDER` AND `BORROWER` should not be the same person.

Example of usage:
```
add transaction Alice p/Bob a/10
addt Bob p/Alice a/5
at Alice p/Bob a/7

// Multiple Borrowers
add transaction Alice p/Bob a/10 p/Charlie a/5

// Dated Transaction
add transaction Alice t/11-11-2000 2359 p/Bob a/10
    Transaction added successfully!
    Lender: alice
    Transaction time: 11-11-2000 2359
    Borrower 1: bob Owed amount: $10.00
```

### Adding a new group `add group`

Adds a new group to LongAh! for you to manage expenses and debts separately for different groups of people.

Format: `add group [GROUP_NAME]` OR `addg` OR `ag`

* The Application will automatically prompt you to create a new group if this is your first time using LongAh!.
* The Application will not automatically switch to the new group after adding. You can switch to the new group using the `group` command.
* The entered group name should not be a duplicate of an existing group.
* The entered group name should only contain alphanumeric characters, and no spaces are allowed.
* The name of the group is case-sensitive. i.e. 'Tiktok' and 'tiktok' are not considered the same group.
* Group names are limited to 50 characters.

Example of usage:
```
add group Tiktok
addg Tiktok
ag Tiktok
    Added group: Tiktok
```

### Listing all members: `list members`

Shows a list of all current members in the group along with their current balances.

Format: `list members` OR `listm` OR `lm`

* Positive balance indicate that the member is owed money by other member(s) in the group.
* Negative balance indicate that the member owes money to other member(s).
* A balance of 0 indicates that the member neither owes nor is owed money.

Example of usage:
```
add member alice
add member bob
add transaction alice p/bob a/5

list members
listm
lm
    alice: $5.0
    bob: -$5.0
```

### Listing all transactions: `list transactions`

Shows a list of all transactions in LongAh!.

* Each transaction is indexed for easy reference and use in other commands.

Format: `list transactions` OR `listt` OR `lt`

Example of usage:
```
add member alice
add member bob
add transaction alice p/bob a/5

list transactions
listt
lt
    1.
    Lender: alice
    Borrower 1: bob Owed amount: 5.00
```

### Listing all debts: `list debts`

Calculates the simplest way to repay all debts between all members and shows a list of all debts in LongAh!.

Format: `list debts` OR `listd` OR `ld`

Example of usage:
```
add member alice
add member bob
add member charlie
add transaction alice p/bob a/3 p/charlie a/4
add transaction charlie p/alice a/5 p/bob a/1

list debts
listd
ld
    Best Way to Solve Debts:
    bob owes charlie $2.0
    bob owes alice $2.0
```

### Listing all groups: `list groups`

Shows a list of all groups in LongAh!.

Format: `list groups` OR `listg` OR `lg`

Example of usage:
```
// assume that the group 'Tiktok' already exists

add group Friends
add group Family

list groups
listg
lg
    1. Tiktok
    2. Friends
    3. Family
```

### Find Transactions: `find transactions`

Finds all transactions that involves the specified member. The member can be involved as a lender or a borrower in the transaction(s).

Format: `find transactions [MEMBER]` OR `findt` OR `ft`
* The `MEMBER` should be an existing member.

Example of usage: 
```
add member Alice
add member Bob
add transaction Alice p/Bob a/5
add transaction Bob p/Alice a/3

find transactions Alice
findt Alice
ft Alice
    Alice is a part of the following list of transaction(s).
    1.
    Lender: Alice
    Borrower 1: Bob Owed amount: $5.00
    
    2.
    Lender: Bob
    Borrower 1: Alice Owed amount: $3.00
```

### Find Lender `find lender`

Finds all transactions where the specified member is the lender.

Format: `find lender [MEMBER]` OR `findl` OR `fl`
* The `MEMBER` should be an existing member.

Example of usage:
```
// Continuing from above example

find lender Alice
findl Alice
fl Alice
    Alice is a lender in the following list of transaction(s).
    1.
    Lender: Alice
    Borrower 1: Bob Owed amount: $5.00
```

### Find Borrower `find borrower`

Finds all transactions where the specified member is a borrower.

Format: `find borrower [MEMBER]` OR `findb` OR `fb`
* The `MEMBER` should be an existing member.

Example of usage:
```
// Continuing from above example

find borrower Alice
findb Alice
fb Alice
    Alice is a borrower in the following list of transaction(s).
    1.
    Lender: Bob
    Borrower 1: Alice Owed amount: $3.00
```

### Find Debts `find debts`

Finds all debts that the specified member has with other members.

Format: `find debts [MEMBER]` OR `findd` OR `fd`
* The `MEMBER` should be an existing member.

Example of usage:
```
// Continuing from above example
add member Charlie
add transaction Alice p/Charlie a/3

find debts Alice
    Bob owes Alice $2.0
    Charlie owes Alice $3.0

add transaction Charlie p/Alice a/10

findd Alice
fd Alice
    Alice owes Charlie $5.0
```

### Deleting a member: `delete member`

Deletes a member from the list of members in the group.

Format: `delete member [MEMBER]` OR `deletem` OR `dm`
* The `MEMBER` should be an existing member.
* All transactions involving the member will be deleted.
* All debts involving the member will be recalculated.

Example of usage:
```
delete member Alice
deletem Alice
dm Alice
    Deleted member: Alice
```

### Deleting a transaction: `delete transaction`

Deletes a transaction from the list of transactions in the group.

Format: `delete transaction [TRANSACTION_INDEX]` OR `deletet` OR `dt`
* The `TRANSACTION_INDEX` should be an existing transaction number.
* All debts involving the transaction will be recalculated.
* The transaction number can be found by using the `list transactions` command and taking the corresponding index of 
the transaction that you want to delete.

Example of usage: 
```
delete transaction 3
deletet 3
dt 3
    Transaction #3 removed successfully.
    Lender: Alice
    Transaction time: 11-11-2000 2359
    Borrower 1: Bob Owed amount: $10.00
```

### Deleting a group `delete group`

Deletes a group from LongAh!.

Format: `delete group [GROUP_NAME]` OR `deleteg` OR `dg`
* The `GROUP_NAME` should be an existing group.
* All transactions and members in the group will be deleted, and the group will be removed from the list of groups.
* The Application will automatically switch to the first group in the list if the current group that you are managing is deleted.
* If all groups are deleted, the Application will automatically prompt you to create a new group.

Example of usage:
```
// assume that the group 'Tiktok' already exits

add group friends
delete group friends
deleteg friends
dg friends
    Remaining groups:
    1. Tiktok

Deleted group: friends
```

### Editing a member: `edit member`

Edits the name of a member in the list of members in LongAh!.

Format: `edit member [OLD_NAME] p/[NEW_NAME]` OR `editm` OR `em`
* `p/` is the prefix for the new name of the member.
* The `OLD_NAME` should be an existing member.
* The `NEW_NAME` should not be a duplicate of an existing member.
* All transactions involving the member will be updated to reflect the new name.

Example of usage:
```
edit member Alice p/Bob
    Member name edited successfully! Alice is renamed to: Bob
```

### Editing a transaction: `edit transaction`

Edits the details of a transaction in the list of transactions in LongAh!.

Format: `edit transaction [TRANSACTION_INDEX] [LENDER] p/[BORROWER1] a/[AMOUNT] p/[BORROWER2] a/[AMOUNT] ...` OR `editt` OR `et`
* The `TRANSACTION_INDEX` should be an existing transaction number.
* The `LENDER` and `BORROWER(s)` should be an existing member.
* Transaction date and time can similarly be edited or added through the same format as per [add transaction](#adding-a-transaction-add-transaction)
* Allows for edits to the lender and the borrowers involved in the transaction, as well as the amount.
* The transaction number can be found by using the `list transactions` command and taking the corresponding index.
* All debts involving the transaction will be recalculated.

Example of usage:
```
add member Alice
add member Bob
add member Charlie
add transaction Alice p/Bob a/1

edit transaction 1 Bob p/Alice a/3
editt 1 Bob p/Alice a/3
et 1 Bob p/Alice a/3
    Transaction #1 edited successfully.
    Lender: Bob
    Borrower 1: Alice Owed amount: $3.00
```

### Enabling the user PIN: `pin enable`

Enables user PIN authentication for the application. (disabled by default)

Format: `pin enable`

Example of usage: 
```
pin enable
    Authentication enabled upon startup.
```

### Disabling the user PIN: `pin disable`

Disables user PIN authentication for the application. 

Format: `pin disable`

Example of usage:
```
pin disable
    Authentication disabled upon startup.
```

### Resetting user PIN: `pin reset`

Resets the user's PIN for the application. Follow the instructions as prompted to reset the PIN.

Format: `pin reset`
* The new PIN should only contain numbers (0-9).

Example of usage:
```
pin reset
    Enter your current PIN: 654321
    Create your 6-digit PIN: 123456
    PIN saved successfully! You can enter 'pin enable' to enable authentication upon startup.
```

### Clearing all transactions `clear`

Clear all previous transactions logged in the group. Members balances will be reset to 0.

* The application will prompt you to confirm the action before clearing all transactions.
* This action cannot be undone.

Format: `clear`

Example of usage:
```
add member Alice
add member Bob
add transaction Bob p/Alice a/3
clear
    Are you sure you want to clear all transactions? (Y/N)
    This action cannot be undone. All transaction data will be lost.
    Enter 'N' or any other key to cancel.
    
y
    All transaction records have been cleared.
    All transactions have been cleared for this account.
```

### Settle a user's debts: `settleup`

Settles all debts of the specified member with all other members. A transaction will be created to settle the debts and reset
the debt balance of the specified member to 0, while updating the balance(s) of all relevant lender(s).

Format: `settle [MEMBER]` OR `settleup [MEMBER]`
* The `MEMBER` should be an existing member.
* The `MEMBER` should be a valid debtor in the group (i.e. the member should owe money to other members).

Example of usage:
```
add member alice
add member bob
add member charlie
add transaction alice p/bob a/3 p/charlie a/4
add transaction charlie p/alice a/6 p/bob a/1

list debts
    Best Way to Solve Debts:
    bob owes alice $1.0
    bob owes charlie $3.0

settleup bob
settle bob
    bob has repaid alice $1.0
    Transaction added successfully!
    bob has repaid charlie $3.0
    
    Transaction added successfully!
    Lender: bob
    Borrower 1: alice Owed amount: $1.00
    Borrower 2: charlie Owed amount: $3.00

    bob has no more debts!
``` 

### Switching groups: `group`

Switches to the specified group in LongAh!.

Format: `group [GROUP_NAME]`
* The `GROUP_NAME` should be an existing group that has been added to LongAh!.

Example of usage:
```
// assume that the user is currently managing group 'Tiktok'
add group friends
    Added group: friends
    
group friends
    Switching groups...
    You are now managing: friends
```

### Filter transactions: `filter`

Filters transactions based on the time of the transaction.

Format: `filter time a/[TIME] b/[TIME]` OR `filter time a/[TIME]` OR `filter time b/[TIME]` OR `filter time`
* The `TIME` should be in the format `dd-MM-yyyy HHmm`.
* The `a/` prefix is used to filter transactions that occurred after the specified time.
* The `b/` prefix is used to filter transactions that occurred before the specified time.

Example of usage:
```

```

### Views the balances of all members on a chart: `chart`

Shows a chart of the balances of all members in the group.

Format: `chart`

Example of usage:
```
add member alice
add member bob
add member charlie
add transaction alice p/bob a/100
add transaction charlie p/alice a/6 p/bob a/1

chart
    Loading balances chart...
```

A separate window will pop up displaying the balances of all members in the group in the form of a category chart.

They are color-coded to show the balance status of each member.

A separate tooltip will show the exact balance of each member.

![viewChart.png](diagrams/viewChart.png)

### Exiting the application: `exit`

Exits the application.

Format: `exit` or `close`

Example of usage:
```
exit
close
    Goodbye! Hope to see you again soon!
```

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install LongAh! on the other computer and replace the empty members, pin, and transaction TXT files it creates with the files containing your data.

## Known Issues

## Future Improvements

The following quality of life improvements have been taken into consideration and will be implemented in future versions of LongAh!

1. Edit Group Names
2. Settle with Reference to Specific Lender Only