Feature: Login en Swag Labs

  Scenario: Login exitoso con usuario válido
    Given el usuario navega a la página de login
    When ingresa usuario "standard_user"
    And ingresa password "secret_sauce"
    And hace clic en Login
    Then debería visualizar el inventario de productos

  Scenario: Login fallido con usuario bloqueado
    Given el usuario navega a la página de login
    When ingresa usuario "locked_out_user"
    And ingresa password "secret_sauce"
    And hace clic en Login
    Then debería visualizar mensaje de error
    