Hack The North - Coding Challenge 
===========

HTN '15 Mobile Interview Challenge

Native Android mobile application buillt of which displays HTN attendee profile data on a map with real-time pins base off user info.

<div>
<img src="https://github.com/Mrez95/HTN-Hackers/blob/master/app/src/main/res/drawable/final_smash.jpg" alt="Drawing" width="300" height="500" style="float:left" />

<img src="https://github.com/Mrez95/HTN-Hackers/blob/master/app/src/main/res/drawable/11004014_944324105579755_773804323_n.jpg" alt="Drawing" width=300" height="500" style="float:right"/>
</div>
<b>Important Considerations</b><br>
- Is the base specification implemented? <br>
- Have you used appropriate networking, JSON, etc. libraries in retrieving data from Firebase? <br>
- Is the UI performant? For the list, is scrolling smooth despite loading images from the network? For the map, is drawing all the elements onto the map smooth despite there being more than 1200 of them?

Data 
------------
We have 1200+ fake user profiles complete with geographic information and skills listings located at https://htn15-interviews.firebaseio.com/.json. The JSON schema is:


```javascript
{
  “name”: <string>,
  “picture”: <string>
  “company”: <string>,
  “email”: <string>,
  “phone”: <string>,
  “latitude”: <float>,
  “longitude”: <float>,
  “skills”: [
    {
      “name”: <string>,
      “rating”: <float>
    }
  ]
}
```
Features
------------

+ Firebase REST API
+ Google Maps API
+ GSON 
+ POJO
+ JSON

Usage
------------

Pull repository and build on local machine.

License
-------------

Copyright (c) 2015 Eddie Zhang.

_________________________
