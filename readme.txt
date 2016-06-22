/**
 *
 * BetterCloud Programming Exercise
 * @author: Lovissa Winyoto
 * @version 1.0
 *
 */

 ******************************************************************************
                              FILES & FOLDERS
 ******************************************************************************

 FILES:
 - Log.java: Class that represents the information holder of each log in
    the source files.
 - Process.java: Class containing service provider to process the source files.
    1. consumer
    2. transformer
    3. producer
 - WorkProcess.java: Class that acts as the coordinator/controller of the whole
    process. The class contain the main method.

 FOLDERS:
 - logs: contains the files to be processed.
 - logs_tally: contains the result of the processed files.
 - test: contains files that are created specifically for testing purposes.

 ******************************************************************************
                                   TESTS
 ******************************************************************************

 The tests for the code is written in JUnit

 - TestConsumer: Exhaustive test the consumer method in Process.java .
 - TestTransformer: Exhaustive test the transformer method in Process.java .
 - TestProducer: Exhaustive test the producer method in Process.java .

 ******************************************************************************
                              DESIGN PRINCIPLES
 ******************************************************************************

 This programming exercise follows the Object Oriented Design Principles. The
 applied S.O.L.I.D principles examples are:
 - The methods in the process class exactly do a specific task that is
  represented in the method name. Each method has modular responsibility, and
  each method only does exactly what it describes (Single-Responsibility
  Principle)
 - The design of the program applies the open-closed principle where it is
  closed to any modification to the existing code but will be open for
  code extension in the future. Although not all of the information is useful
  for the current requirement, it organized the information in a way that
  allows future developers to use this code and extend it to achieve other
  tasks.

 ******************************************************************************
                                   LIMITATION
 ******************************************************************************

 LIMITATION:
 - The program currently takes in hard-coded directory and utilizes manually
  made folders
 - The program currently does not support a user interface to allow easier
  interaction between the user and the program