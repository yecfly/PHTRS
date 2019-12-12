<%-- 
    Document   : report
    Created on : 2013-7-4, 20:35:13
    Author     : Yecfly
--%>

<center>
    <table><tr><td align="left"><h3>Welcome to report page, ${sessionScope.Caccount.name}!</h3></td>
            <td><form name="logout" action="/phtrs/loginhandler" method = "post">
                    <input type="submit" name="logout" value="logout"/>
                </form></td></tr>

        <br/>
        <br/>
        <tr><td><h4>Status:${sessionScope.ReportSuinfo}</h4></td></tr>
        <form name="report" action="/phtrs/report" method="post">
            <tr>
                <td>
                    <p>Street Address:<input type ="text" name ="streetAddr" size="30" maxlength="30"/></p>
                </td>
                <td>
                    <p>Size:<select name="size">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>District:<input type="text" name="district" size="20" maxlength="20"/></p>
                </td>
                <td>
                    <p>Location:<select name="location">
                            <option value="middle">middle</option>
                            <option value="curb">curb</option>
                        </select></p>
                </td>
            </tr>
            <tr><td align="left"><br/><br/>Please write down your information for the feedback message.</td></tr>
            <tr>
                <td>
                    <p>Name:<input type="text" name="CName" size="20" maxlength="20"/></p>
                </td>
                <td>
                    <p>Phone:<input type="number" name ="CPhone" size="15" maxlength="15"/> </p>
                </td>
            </tr>
            <tr>
                <td><p>Address:<input type="text" name="CAddr" size="50" maxlength="50"</p></td>
            </tr>
            <tr><td><input type="submit" name="submit" value="submit"/></td></tr>
        </form>
    </table>
</center>