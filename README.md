# Chicago-Crime-Data-Analysis
An analysis of Chicago crime data using Apache Spark. The data can be pulled from the [Chicago Crime Data Link](https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2). A link to code for analysis is present with every result(Please refer the uncommented code)

#### Technologies Used
 - Scala
 - Spark Core APIs

## Questions Answered about data

*There were 594681 cases with no mention of community numbers so they are excluded from the analysis*

#### Top 10 communities with most crime
| Community Code        | Community Name           | Crime Numbers  |
| ------------- |:-------------:| -----:|
|25 | Austin | 368568|
|43 | South Shore | 186096|
|23 | Humboldt Park | 181174|
|67 | West Englewood | 169988|
|24 | West Town | 168683|
|28 | Near West Side | 164947|
|29 | North Lawndale | 163847|
|71 | Auburn Gresham | 161269|
|68 | Englewood | 152333|
|49 | Roseland | 149107|

#### Top 10 communities with least crime
| Community Code        | Community Name           | Crime Numbers  |
| ------------- |:-------------:| -----:|
|47 | Burnside  | 8525|
|12 | Forest Glen | 10414|
|74 | Mount Greenwood | 12113|
|55 | Hegewisch | 12433|
|36 | Oakland | 12672|
|18 | Montclare | 13842|
|13 | North Park | 18322|
|57 | Archer Heights | 19661|
|37 | Fuller Park | 19693|
|72 | Beverly | 20673|

#### Top 3 Crime Types
| Crime Type        | Percentage (%)          |
| ------------- |:-------------:| 
| THEFT | 20.91 |
| BATTERY | 18.25 |
| CRIMINAL DAMAGE | 11.47 |

#### What months do criminal like? 
| Month Number | Number of cases  | Percentage (%) |
| ------------- |:-------------:| :-------------:| 
| 7 | 605183 | 9.3 |
| 8 | 598999 | 9.2 |
| 5 | 578254 | 8.88 |

#### What months have lower criminal activities?
| Month Number | Number of cases  | Percentage (%) |
| ------------- |:-------------:| :-------------:| 
| 2 | 446463 | 6.86 |
| 12 | 478061 | 7.35 |
| 1 | 507500 | 7.8 |

*Seeing the above results, we see a pattern that criminals have preferred warmer months to colder months such as Dec for their activities*

#### What is the most unsafe time to be in the streets?
| Time | Number of cases  | Percentage(%)  |
| ------------- |:-------------:| :-------------:|
| 08 PM | 371513 | 5.71 |
| 07 PM | 370697 | 5.7  |
| 12 PM | 369403 | 5.68 |
| 09 PM | 363716 | 5.59 |
| 12 AM | 361560 | 5.56 |

#### What is the most safe time to be in the streets?
| Time | Number of cases  | Percentage(%)   |
| ------------- |:-------------:| :-------------:|
| 05 AM | 86597 | 1.33 |
| 06 AM | 102302 | 1.57 |
| 04 AM | 104799 | 1.61 |
| 03 AM | 139686 | 2.15 |
| 07 AM | 147395 | 2.26 |

*So according to the data, while your morning walk will be pleasant and safe, you need to be careful when you leave your office in the evening or return back home after a drink at the local bar*

#### Which is the most unsafe street (100XX W OHARE ST with 14952 cases)
| Crime Type | Number of cases  | Percentage(%)    |
| ------------- |:-------------:| :-------------:|
| THEFT | 5237 | 35.03 |
| OTHER OFFENSE | 2568 | 17.17 |
| CRIMINAL TRESPASS | 1811 | 12.11 |
| DECEPTIVE PRACTICE | 1386 | 9.27 |
| BATTERY | 931 | 6.23 |
| NARCOTICS | 853 | 5.7 |
| CRIMINAL DAMAGE | 812 | 5.43 |
| MOTOR VEHICLE THEFT | 455 | 3.04 |

#### Which is the most safe street ( 027XX E 126TH ST with 1 case)
| Crime Type | Number of cases  | Percentage(%)    |
| ------------- |:-------------:| :-------------:|
| CRIMINAL DAMAGE | 1 | 100.0 |

#### Is Chicago PD doing a good job? 
| Arrest Done | Number of cases  | Percentage(%)    |
| ------------- |:-------------:| :-------------:|
| No | 4519824 | 71.9 |
| Yes | 1766253 | 28.1 |

*Going by the numbers, we see that arrest was done on a very small percentage of criminal cases*

## Contributions

Open an issue for more questions which you think can be answered through this data and raise a PR if you find any bugs/improvements in the code
