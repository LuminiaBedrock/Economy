# Economy
Экономика для сервера SoulLands

## Добавление зависимости
```xml
<dependency>
    <groupId>com.clubdev.economy</groupId>
    <artifactId>Economy</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/lib/Economy-1.1.jar</systemPath>
</dependency>
```

## API
### Получение инстанса экономики:
```java
Economy economy = Economy.getInstance();
```
### Получение менеджера экономики:
```java
EconomyManager manager = economy.getEconomyManager()
```
Все методы доступные для использования можно увидеть тут:
[EconomyManager.java](https://github.com/ClubDevelopment/Economy/blob/main/src/main/java/com/clubdev/economy/managers/EconomyManager.java)

## [События](https://github.com/ClubDevelopment/Economy/tree/main/src/main/java/com/clubdev/economy/event)
Примечание: все приведенные события ниже являются отменяемыми и могут быть отменены методом `event.setCancelled()`

### SetMoneyEvent
Вызывается при установке денег пользователю

`double money` - установленные деньги,   
`Account account` - аккаунт которому были добавлены деньги.

### AddMoneyEvent
Вызывается при добавлении денег пользователю

`double money` - добавленые деньги,   
`Account account` - аккаунт которому были добавлены деньги.

### ReduceMoneyEvent
Вызывается при забирании денег у пользователя

`double money` - забранные деньги,   
`Account account` - аккаунт которому были добавлены деньги.

### PayMoneyEvent
Вызывается при переводе денег между пользователями

`Double money` - переведенные деньги,    
`Account player` - аккаунт пользователя который перевел деньги,   
`Account target` - аккаунт пользователя которому перевели деньги.

### CreateAccountEvent
Вызывается при создании аккаунта пользователя

`UUID uuid` - UUID пользователя.
