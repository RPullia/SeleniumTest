Feature: using the youtube searchbar

  Scenario: I want to search something on youtube
    Given that I'm on the youtube homepage
    When I write something in the searchbar
    And I click on the search button
    Then the result should be what I'm looking for