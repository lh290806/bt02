<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach Survey - Thanks</title>
    <link rel="stylesheet" href="main.css" type="text/css">
</head>
<body>
    <main class="survey-container">
        <h1 class="thanks-header">Thanks for joining our email list</h1>

        <p class="intro-text">Here is the information that you entered:</p>

        <div class="result-grid">
            <label>First Name:</label>
            <span>${user.firstName}</span>

            <label>Last Name:</label>
            <span>${user.lastName}</span>

            <label>Email:</label>
            <span>${user.email}</span>

            <label>Date of Birth:</label>
            <span>${user.dob}</span>

            <label>Heard about us from:</label>
            <span>${survey.hearAbout}</span>

            <label>Contact me by:</label>
            <span>${survey.contactBy}</span>

            <label>Announcements:</label>
            <span>${survey.announcements}</span>
        </div>

        <br>

        <a href="index.html" class="return-btn">Return</a>
    </main>
</body>
</html>