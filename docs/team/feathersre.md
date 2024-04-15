# Liao Jingyu - Project Portfolio Page

## Overview

LongAh! is a CLI-based application designed to help users track debts within friend groups and determine the least 
transaction method of settling these debts. It is optimized for busy people with large transaction quantities among friends. It is written in Java.

### Summary of Contributions

My contributions towards the project is as follows.

* **New Feature**: Finding Transactions (by Member name in general/Lender/Borrower)
  * What it does: This feature provides users with the convenience of obtaining a partial list of transactions 
  consisting only entries that the input member is participating in.
  * Justification: Constructed algorithms capable of varying printout by flexibly adding in transactions based on whether
  they fulfill the user defined member name criteria.
  * Highlights: Based on the input keyword (Lender/Borrower) the list can be further varied to only look for pending 
  payments (Lender), pending transactions (Borrower) or both.

* **New Feature**: Transaction Time
  * What it does: This feature allows the time component of transactions to be tracked and handled, further 
  enhancing LongAh!'s ability in reminding users of their pending transactions.
  * Justification: Introduction of the DateTime class. Included further polymorphism in methods of the Transaction class
  to meet the needs to add, print and store dated transactions.
  * Highlights: All time handling operations are throughout encapsulated within the DateTime class. This greatly reduces
  code coupling, allowing the formatting of date time variables to be done easily in the system.

* **New Feature**: Filter Transactions (by Transaction Time)
  * What is does: This feature allows users to freely filter the current list of dated transactions based on their 
  defined time criteria, further enhancing accessibility.
  * Justification: Introduction of the filter commands class. Added comparison methods within the DateTime class and new
  methods in TransactionList to account for this feature.
  * Highlights: Users are given the privilege to choose between 4 modes of filtering. This could be applied to select
  transactions after a date, before a date, between two dates and happening exactly at a date.

* **General Contributions**: String printout of transactions and transaction lists. Comparison methods between member
objects. Initial extraction of logs into external file.

* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=feathersre&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project Management**:
  * Participated actively in the distribution of work and issues in the respective milestones
  * Facilitated discussion in weekly meetings to breakdown assigned workload to manageable sub-tasks.

* **Documentation**:
  * User Guide
    * Formatted content regarding dated transactions and time filters [#97](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/97) [#175](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/175)
    * Added in directory tree to visualise program storage structure [#97](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/97)
    * Added in common errors to provide general guide for program mishandling [#175](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/175)
  * Developer Guide
    * Increment amendments for dated transactions. [#97](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/97)
    * Added implementation details for DateTime. [#175](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/175)

* **Community**:
  * Participated in PRs reviews with non-trivial review comments. (e.g.: [#43](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/43) [#121](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/121) [#153](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/153))
  * Actively exchanged implementation ideas with team members in authored PRs. (e.g.: [#38](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/38) [#77](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/77) [#156](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/156))