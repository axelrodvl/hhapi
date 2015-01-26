# hhapi
HeadHunter.ru API Testing

Automated tests for API.me method and OAuth tools

Using:
- Maven: build automation tool
- JUnit: test creation
- Apache Oltu: OAuth requests generation
- Selenium: working with websites
- Google Simple JSON: JSON handling

Tests list:
1) TestGetPositive
- getInfo
2) TestGetNegative
- incorrectAccessToken
3) TestPostPositive
- setNameFull
- setNameFirstAndLast
- setIsInSearchTrue
- setIsInSearchFalse
- setIsInSearchIncorrectString
- setIsInSearchIncorrectStringNumbers
4) TestPostNegative
- setNameEmptyFullName
- setNameEmptyFirstName
- setNameEmptyLastName
- setNameOnlyLastName
- setNameOnlyFirstName
- setNameOnlyMiddleName
- setNameOnlyLastAndMiddleName
- setNameOnlyFirstAndMiddleName
- setNameAndIsInSearch
- incorrectAccessToken

Tests description (in Russian):
1. Получение информации
        1.1 Положительные тесты
          1.1.1 Получение информации о пользователе (соискателе)
        1.2 Отрицательные тесты
          1.2.1 Попытка доступа с некорректным accessToken
2. Редактирование информации
        2.1 Положительные тесты
          2.1.1 Редактирование фамилии, имени и отчества
          2.1.2 Редактирование фамилии и имени (отчество пустое)
          2.1.3 Редактирование флага "ишу/не ищу работу" = true
          2.1.4 Редактирование флага "ишу/не ищу работу" = false
          2.1.5 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
          2.1.6 Передача численного значения во флаг "ищу/не ищу работу"
        2.2 Отрицательные тесты
          2.2.1 Пустое ФИО
          2.2.2 Пустое имя
          2.2.3 Пустая фамилия
          2.2.4 Только фамилия
          2.2.5 Только имя
          2.2.6 Только отчество
          2.2.7 Только фамилия и отчество
          2.2.8 Только имя и отчество
          2.2.9 Редактирование имени вместе с флагом "ищу/не ищу работу"
          2.2.10 Попытка доступа с некорректным accessToken

