# Unit Tests for TUM Informatic's Homework

## **Disclaimer**: Do not solely rely on these Unit Tests for correction. Mistakes happen and sometimes things may slip past the tests. 

### Availible Subjects
* EIDI 1 WS16
* GAD SS17

### Contact
* [Announcement / News Channel](https://telegram.me/TUMUnitTests)
* [Chat](https://telegram.me/joinchat/CBbdCQtAOI9Qalx6JlYdcw)

**You can also notify us of issues and bugs by submitting a GitHub issue.**

### Purpose of a Unit Test 

The Unit Test's purpose, is to help identify errors in the programs written for PGDP and GAD. It does this by creating test cases, in which inputs are entered into the classes, methods being run and finally checking to see if the output is what is to be expected.

### Setup 

The setup of the UnitTests vary depending on the IDE. If your IDE is not listed here, post it as an issue.

##### Eclipse

    1. Select the Project
    2. Click: Project > Properties
    3. Click: Java Build Path > Libraries > Add Library...
    4. Click: Junit > Next > Select Junit 4 (or higher) > Finish

>https://youtu.be/CD5JJ5bDAfY?t=2m33s

##### NetBeans

    1. Select the Project
    2. Right click on Libaries
    3. Add Libary
    4. Select "Junit 4.12" (or higher) and add libary
    5. Right Click on Project > New > Other > Junit Test
    6. Move your method and the method's test class into the Junit Test
    7. Right Click on MethodNameTest > Test File

>https://www.youtube.com/watch?v=C6oQqbqPBB0

##### IntelliJ

    1. Either put the Test and Production Code (your code) into the TestFolder
    2. Or be sure to fix your imports and scope (it is just a bit cleaner).

>https://www.youtube.com/watch?v=Bld3644bIAo

##### BlueJ
BlueJ automatically comes with JUnit, thus you will not need to import it into your project. Be aware that BLUEJ uses an OUTDATED version of JUNIT, thus may not accept all methods used in the test. It is advisable to have eclipse of another IDE for testing purposes.

    1. Copy the Test Files into your Project
    2. Restart BlueJ
    3. Compile. The Unit Test should turn Green
    4. Right Click on the Unit Test and hit Test All (or the specific Test you require)
