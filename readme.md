# .gitignore generator

MVP: 
- jq java gradle javascript

Functionalities:
- guess that should be the content of the .gitignore file 
- allow user to provide keywords (fix them with Levenstein if needed)
- run natively (no java needed)

## How it works under the hood:


User calls the program with parameters

jq java 
  |
  |
  |
  V
  
 Program runs a list of Handelers, which call Services to get inforamtion.
 ExecutionContext file is used to keep and retrive execution data 