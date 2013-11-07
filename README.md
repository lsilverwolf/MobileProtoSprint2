<h1>Mobile Prototyping Sprint 2: CHAOS</h1>

CHAOS (connecting helpful alumni and Olin students) is an app that strives to lower the barrier between alumni and student interactions. Each user of the app creates a profile with their name, graduation year, a blurb about themselves, a list of things that they’d like to ask others about, and a list of things that they are comfortable answering questions about. Then in the user’s home screen, they see the names, graduation years, and blurbs from several other users of the app and can click on any of these users to view their lists as well as to connect with them through a “hi” or a message. A user can also search the people in the app for a specific username, a topic they would like to learn more about or a topic they want to answer questions on.

This sprint was done for Evertrue, a Boston-based mobile networking platform for alumni communities.

<h2>Implementation</h2>
<h3>Data</h3>
We ultimately hard-coded in much of the data for this app despite the fact that we created a server-side database for storing the data. The user profile was hard-coded with no way to currently edit it, and the other fake user profiles were hard-coded in the server database.

<h3>Main Feed Fragment</h3>
The main view displayed all results from the server as expected (there were actually multiple copies of each user in the server). However, when the user clicked on any of the profiles, they all opened to Jane Roe’s detail view profile. This particular detail view profile displayed as expected, but we have not idea why it was the only one that came up when any of the results were clicked on.

<h3>Detail Activity</h3>
Inside of the detail view there were two buttons, one for sending a message and one for sending a “hi.” The message button opened to a new fragment where a user could type a message, but the we never implemented the functionality of getting the text from the box and saving it into the server to be displayed to the recipient of the message. The “hi” button displayed a view that said the “hi” was sent, but again, the “hi” was never stored to the server to be viewed by its recipient.

<h3>Search Fragment</h3>
The search feature worked to the best of our knowledge, although we were having some problems with it hypothetically not displaying all of the results. This view also suffered from the problem that when you clicked on any user in the results list, the detail view opened to Jane Roe.

<h3>User Profile Fragment</h3>
Finally in the profile fragment, all of the data displayed as expected, except that the full lists did not show up in the view. We tried to implement a scroll view so that we could just scroll the whole view basically, to view the entirety of everyone’s list, but it did not end up working. 

<h3>Message Inbox Activity</h3>
At the top of the user profile, we had a button that displayed the user’s inbox. This was not the best design decision to place the inbox bar inside of the user’s profile fragment, but we thought that we were more likely to be able to get it to work if we put it here. However, after several hours of staring at it and trying to de-bug it, we still were unable to get the message activity to open and display the messages.