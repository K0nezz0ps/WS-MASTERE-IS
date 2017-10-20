<form method="POST" action="/WS-MASTERE-IS/auth/login">

	<div>
		<label>E-mail :</label> <input type="text" placeholder="Your username" name="inputEmail" />
	</div>
	<div>
		<label>Password :</label> <input type="password" placeholder="Your password" name="inputPassword" />
	</div>
	<div>
		<input type="submit" value="Connect" />
	</div>

</form>

<p style="color: red;">${error}</p>
<p style="color: green;">${validation}</p>