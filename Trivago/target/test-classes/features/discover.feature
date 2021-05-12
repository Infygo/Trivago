Feature: Discover tab of Trivago site 


Scenario Outline: E2E process of Viewing deals in the discover tab of Trivago site 
Given Initialise driver and navigate to Trivago homepage
When  Click on Disover tab 
When  Input city location <city>
When  Input Radius <radius> Stay dates <staydates> <staynights> and Guests <adults> <children> <rooms>
When  Change currency to Euros 
When  Deals are displayed click View deal
Then  Verify if new tab is opened displaying the deals 
And   Close the browser

Examples:
|city         |radius    |staydates   |staynights|adults|children|rooms |   
|Berlin       |1         |Next 14 days|-         |1     |1       |1     |
|Stuttgart    |2         |March       |+         |2     |0       |1     |
|Hamburg      |3         |April       |+         |3     |2       |2     |
|Cologne      |4         |May         |+         |4     |2       |3     |







