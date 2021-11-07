---
layout: page
title: Joel Chan's Project Portfolio Page
---

## Project: InsurancePal

InsurancePal is a client management software specially designed
to help manage the logistics of being an insurance agent.

### Summary of Contributions
Given below are my contributions to the project.

**Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=JoelChanZhiYang&tabRepo=AY2122S1-CS2103T-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

**Major Enhancements**
* Implemented **claims command** ([#69](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/69), [#101](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/101), [#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
  * **What it does**: The claim command allow users to add claims to the model.
  * **Justification**: Part of the role of an insurance agent is managing the insurance 
    claims of the client. The claim command allows the user to easily add, edit and delete 
    claims from the model, providing a new feature that would greatly benefit the target user
    of insurance agents.
  * **Highlights**:
    * This feature involves modification to the `Logic`, `Model` and `Storage` components, requiring a 
      good understanding of how the entire application is interconnected. 
    * The `claim` command can add, edit and delete claims based on the title provided, and the number of 
      parameters given. Hence, its implementation require non-trivial logic to handle the different cases
      of valid and invalid inputs. 
    * Due to the high number of valid inputs, a substantial amount of automated tests are needed to prevent
      any regressions.
    * Due to complexity of the command, the command required a lot of exception handling to provide useful
      error messages for the user.
      
**Minor Enhancement**
* Made minor modifications to the claims list to resolve a UI bug ([#115](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/115))

**Project Management**:
* Lead discussions during weekly meetings
* Managed the deadlines every milestone
* Manage Github issues for every milestone
* Update project logs
* Update informal demo for v1.2 and v1.3
* Wrap up every milestone
* Release every milestone of GitHub
* Complete any administrative matters for the team

**Team-based Tasks**
* Updated README file for the project ([#26](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/26))
* Update config file for the project ([#21](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/21))
  
**Contributions to User Guide**
* Update user guide to include information about `claim` command ([#22](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/22))
* Fixed all user guide related bugs from PE-D ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Added dropdowns for user constraints for every command ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Modified language in the user guide to be more friendly ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Updated all pictures in user guide ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Added sections "Application Overview" and "How to use this guide" ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Added css styling to some components to for better viewing experience ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Updated "Quick Start" and "Command Summary" ([#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174))
* Made minor edits to user guide at the end of every milestone([#48](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/48), [#115](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/115), [#122](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/122))

**Contributions to Developer Guide**
* Updated developer guide to include information about the implementation of the `claim` command ([#107](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/107))
  * Included sections for 'Current Implementation', "Design Considerations" and "Future Improvements"
  * Created 3 UML diagrams to aid the explanation

**Community**:
* Total PRs reviewed: 34
* PRs Reviewed (with non-trivial comments): [#23](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/23), [#36](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/36), [#66](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/66), [#78](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/78)
* Reported bugs and suggestions for other teams in the class: [14 bugs in PE-D](https://github.com/JoelChanZhiYang/ped/issues)

**Tools**:  
* Added CodeCov integration to the repo
* Set up project website
* Enabled assertions for the project ([#98](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/98))
