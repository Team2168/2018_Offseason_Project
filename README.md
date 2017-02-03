# 2017_Main_Robot
Code for the Steamworks game. This readme provide all of the information required to get started and programming for the 2017 season. 

## Requirements for Robot
1. RoboRio must be flashed to latest 2017 image using USB (This only needs to be done once for the season): (http://wpilib.screenstepslive.com/s/4485/m/13503/l/144984-imaging-your-roborio)
2. Radio must be programed (https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986-programming-your-radio-for-home-use)
3. Roborio IP on ethernet must be set to static using web dashboard to : 10.21.68.2
4. Any IP Camera must be set to 10.21.68.90
5. Vision Processor: If Tegra must be set to 10.21.68.71 (for "TK1"), if Beablebone must be set to 10.21.68.33 (for "BB"), if android must be set to 10.21.68.46 (for "AD")

## Requirements for Students
1. In order to program you need to set up your Java development environment using eclipse. Follow instructions here: https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java (If you already had a previous version of the plugin installed you can simply select help->check for updates)
2. Use JDK 8, and try to use latest Eclipse if possible
3. If you would like to have the driverstation on your computer as well then install NI Update suite, but this is not a requirement to run programs, only to flash robot images (https://wpilib.screenstepslive.com/s/4485/m/13809/l/599671-installing-the-frc-2017-update-suite-all-languages)
4. For returning students: understand what has changed in WPI Librarary since 2016 season (https://wpilib.screenstepslive.com/s/4485/m/13503/l/681378-new-for-2017)
5. Understand how the robot is wired as it affects your code. (https://wpilib.screenstepslive.com/s/4485/m/13503/l/599673-wiring-the-2017-frc-control-system)
## Cool things to know
### Radio
1. You can access radio web page by logging into http://10.21.68.1 root/admin
2. Roborio should always be plugged into the port on the radio labeled "18-24 vPOE" only!

### Roborio
1. You can access roborio diagnostics webpage by http://roboRIO-2168-FRC.local (using IE web browser) or http://10.21.68.2
2. You can program roborio over ethernet, usb, or wifi (if USB, NI Update suite needs to be installed to get usb drivers)
3. More information on the control system can be found at our controls website at http://controls.team2168.org
4. Files will be logged to /home/lvuser/Logs
5. You can ftp files to/from the roborio using filezilla, winscp, web browser, or your local file explorer at ftp://10.21.68.2:21
6. You can ssh into roborio using putty or console application at ssh 10.21.68.2:22 username:lvuser password: blank

### Dashboard (on driver station)
1. Java dashboard will open if Java is selected from the driverstation menu
2. Python dash (if it installed) will open if "default" dashboard is selected from drivestation menu
3. If smartdashboard doesn't update, but you have robot comms, in smart dash preferences toggle "use mDNS" until it does. 

#Repository Guidelines
##Branches
Our repository and workflow loosely follows the gitflow workflow. This workflow is simple and is one of the most popular workflows when using git with a large number of developers. More info: https://www.atlassian.com/git/tutorials/comparing-workflows#gitflow-workflow
- The master branch contains code that is known-working, has been tested, and can be deployed to a competition ready robot.
- The develop branch is our sandbox for integrating and testing new features and fixing problems. This isi the latests and greatest code, but it may have problems and needs to be checked out on the robot before being pushed into master. 
- Everything else is lumpped under feature/bugfix branches. When we need to add new capabilities, start by branching the latest code  in the develop branch.  

## Checklist for committing/pushing code
- Commit often and create detailed log messages that describe what and why you're making a change. Be specific.
- Review the changes you make before pushsing them. You should look through all the files being added/modified/removed before you commit.
- Always verify your code compiles before pushing to the repo. There shouldn't be any red-underlined text in your commits. Use the build button (Green triangle) at the top of eclipse to verify a build completes without error.
- Push your changes into a branch with a name that identifies what feature it is adding or problem it is addressing in the code.
- NEver push to the master branch 
- After pushing your changes to the repo, verify you can see your changes in GitHub from a web browser.

#Robot Design
## Subsystems
### Drivetrain (Aiden)
The drivetrain for 2017, will be largely based off the 2016 drivetrain with minimal changes. Use the 2016 Drivetrain as a reference when developing the 2017 drivetrain. The Drivetrain will have the following member variables
- 4 Victor SP Motor Controllers
- 2 AverageEncoders (one right, one left)
- 1 Gyro (ADRX453) to sense heading of robot 
- 1 Doublesholenoid for shifting between low/high gears

### Shooter Wheel (Krystina)
The shooter for 2017 will be largely based off the 2016 shooter with minimal changes. Use the 2016 Shooter subsystem as a reference when developing the 2017 shooter code. The Shooter will have the following features:
- 2 Talon SRX Motor Controller
- 1 AverageEncoder 

### Shooter Hood (Peter)
- 1 linear servo actuator

### Turret (Ben)
- 1 Spark Motor Controller
- 1 Analog Input - potentiometer for rotational position feedback (turret angle)
- 2 Digital inputs (limit switches) for end of travel detection. These should prevent further rotation in the direction of travel that was triggered. 

### Shooter Indexer (Ben)
- 1 Spark Motor Controller for the indexer roller
- 2 Digital Inputs. Wired to Infrared poximity sensors that identify ball presence/abscence. Ball present = sensor1 OR sensor2. Ball abscent = !sensor1 AND !sensor2

### Ball Intake Roller (Kyle)
The Fuel Ball Intake will be largely based off the 2016 intake with minimal changes. Use the 2016 intake subsystem as a reference when developing the 2017 intake code. The intake will have the following features:
- 1 Spark Motor controller for intake roller

### Ball Intake Arm (Kyle)
- 1 Double Solenoid to extend/retract the intake
- 1 Digital Input - Hall effect sensor - that indicates when the intake is in the retracted position. (this will be used to interlock gear manipulator actuation)

### Gear Intake Roller (Elijah)
- 1 Spark Motor Controller for intaking a gear 
- 1 Analog Input - Sharp IR distance sensor for detecting the presence of a gear in the intake. This sensor will be used to automatically run the intake motors and to auto-raise the intake after capturing the gear.

### Gear Intake Arm (Elijah)
- 1 Double Solenoid. This raises and lowers the gear intake allowing it to be picked up from the floow & scored on the peg
- 1 Digital Input - Hall effect sensor - indicates when the gear manipulator is in the raised position. (this will be used to interlock lowering the intake)

### Conveyor (Nick)
This is a roller system in the bellypan of the robot which feeds balls from the hopper into the lift
- 1 Spark Motor controller

### Elevator (David)
This is a vertical roller system that takes balls from the conveyor and brings them up into the indexer, and ultimately the shooter wheel.
- 1 Spark Motor Controller
- 2 Analog SHARP IR sensors, for ball detection. These are TBD, contingency - not sure if we're goign to need them.

### Climber (John)
This is a simple drum roller that will lift the robot off the ground.
It can only rotate in one direction. So make sure negative values commanded don't actually go out to a motor controller.
- 2 Victor SP Motor Controllers to raise the robot

### Flashlight
- 1 Spike Relay Controller to turn the flashlight on/off.

### ArduinoLights (Elijah)


### Vision (Peter, David)
