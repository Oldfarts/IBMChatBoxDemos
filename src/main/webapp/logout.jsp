<html>
    <head>
        <meta http-equiv="Content-Type" 
            content="text/html; charset=UTF-8"/>
        <title>Login page</title>
        <link href="css/chat.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/chat.js"></script>
    </head>
    <body>
        <form action="logout.go" method="post">
            <div>You have logged out of the chat</div>
            <div>Click login to return the login page</div>
                <!-- listen to keyup to send message if enter pressed -->
                <textarea class="msg-input" onkeyup="chat.dokeyup(event);">Return login page</textarea>
                
            <input type="submit" value="login" />
        </form>
    </body>
</html>