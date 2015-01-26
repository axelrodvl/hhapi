# hhapi
HeadHunter.ru API Testing

API documentation:
- https://github.com/hhru/api/blob/master/docs/general.md
- https://github.com/hhru/api/blob/master/docs/authorization.md
- https://github.com/hhru/api/blob/master/docs/me.md

Automated tests for API.me method and OAuth authorization tools for API access.

Class API.Authorization.java provides access token for API.
Access token can be set two ways in class constructor:
- by user's login and password automatically (application would open authorization page, enter login/password and checking "Provide access to application"). 
- force setting; access token has expire value about 2 weeks, so for testing purposes it can be get once and simply set, without open any page and requests to OAuth server.

Using:
- Maven: build automation tool
- JUnit: test creation
- Apache Oltu: OAuth requests generation
- Selenium: working with websites
- Google Simple JSON: JSON handling

Some test cases may require ChromeDriver standalone application in order to get access token for new application user.
Please, check: https://sites.google.com/a/chromium.org/chromedriver/downloads.

Tests list:
```
1. TestGetPositive
  1.1 getInfo
1. TestGetNegative
  2.2 incorrectAccessToken
3. TestPostPositive
  3.1 setNameFull
  3.2 setNameFullPunctuationMark
  3.3 setNameFirstAndLast
  3.4 setIsInSearchTrue
  3.5 setIsInSearchFalse
  3.6* setIsInSearchIncorrectString
  3.7* setIsInSearchIncorrectStringNumbers
4. TestPostNegative
  4.1** setNameFirstNamePunctuationMarks
  4.2** setNameLastNamePunctuationMarks
  4.3** setNameMiddleNamePunctuationMarks
  4.4** setNameFirstNameNumbers
  4.5** setNameLastNameNumbers
  4.6** setNameMiddleNameNumbers
  4.7 setNameEmptyFullName
  4.8 setNameEmptyFirstName
  4.9 setNameEmptyLastName
  4.10 setNameOnlyLastName
  4.11 setNameOnlyFirstName
  4.12 setNameOnlyMiddleName
  4.13 setNameOnlyLastAndMiddleName
  4.14 setNameOnlyFirstAndMiddleName
  4.15 setNameAndIsInSearch
  4.16 incorrectAccessToken
```
*There is no definition of API's behaviour of sending incorrect value of "is_in_search". That behaviour refered to positive cases by guess: any "is_in_search" values are equals to "false", except of "true".

**There is no definition of API's behaviour of sending incorrect values of first_name, last_name and middle_name. But editing that fields at page http://hh.ru/applicant/settings prints note: "Only letters and hyphen", so this behaviour can be either relate to positive or negative cases, depends on API's requirements of checking values at API client.

Tests description (in Russian):
```
1. Получение информации
  1.1 Положительные тесты
    1.1.1 Получение информации о пользователе (соискателе)
  1.2 Отрицательные тесты
    1.2.1 Попытка доступа с некорректным accessToken
2. Редактирование информации
  2.1 Положительные тесты
    2.1.1 Редактирование фамилии, имени и отчества
    2.1.2 Редактирование фамилии, имени и отчества (значения со знаком "-")
    2.1.3 Редактирование фамилии и имени (отчество пустое)
    2.1.4 Редактирование флага "ишу/не ищу работу" = true
    2.1.5 Редактирование флага "ишу/не ищу работу" = false
    2.1.6* Передача некорректного строкового значения во флаг "ищу/не ищу работу"
    2.1.7* Передача численного значения во флаг "ищу/не ищу работу"
  2.2 Отрицательные тесты
    2.2.1** Некорректное значение имени (знаки кроме "-")
    2.2.2** Некорректное значение фамилии (знаки кроме "-")
    2.2.3** Некорректное значение отчества (знаки кроме "-")
    2.2.4** Некорректное значение имени (числа)
    2.2.5** Некорректное значение фамилии (числа)
    2.2.6** Некорректное значение отчества (числа)
    2.2.7 Пустое ФИО
    2.2.8 Пустое имя
    2.2.9 Пустая фамилия
    2.2.10 Только фамилия
    2.2.11 Только имя
    2.2.12 Только отчество
    2.2.13 Только фамилия и отчество
    2.2.14 Только имя и отчество
    2.2.15 Редактирование имени вместе с флагом "ищу/не ищу работу"
    2.2.16 Попытка доступа с некорректным accessToken
```
*В документации не указано поведение API при передаче некорректного значения флага is_in_search (ищу/не ищу работу) - поэтому данное поведение отнесено к положительным тестам из предположения: любые значения is_in_search, кроме "true", являются идентичными "false".

**В документации не указано поведение API при передаче некорректных значений first_name, last_name и middle_name. Тем не менее, при редактировании этих полей в веб-интерфейсе на странице http://hh.ru/applicant/settings выдается предупреждение "Только буквы и дефис", поэтому данное поведение отнесено к отрицательным тестам, хотя может также являться корректным, в зависимости от требований к проверке передаваемых значений у клиента API. 
