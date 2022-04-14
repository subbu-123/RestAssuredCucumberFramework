Feature: Validation of Google Place API's

  @AddPlace
  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
  
    Given User has Add place payload with <name>, <language>,<address>
    When User hits "AddPlaceAPI" with "POST" HTTP request
    Then User verifies the API call was successful with status code "200"
    And response is validated as per post call json schema
    And "status" in response body is "OK"
    And verify place_Id created maps to <name> using "getPlaceAPI"

    Examples: 
      | name          | language | address               |
      | Salma Kulli   | English  | Kali pahadi ke peeche |
 #     | Babban fatela | Spanish  | San sanati hui gali   |

  @DeletePlace
  Scenario: Verify if Place is being Succesfully deleted using DeletePlaceAPI
  
    Given User has Delete place payload
    When User hits "DeletePlaceAPI" with "POST" HTTP request
    Then User verifies the API call was successful with status code "200"
    And "status" in response body is "OK"
