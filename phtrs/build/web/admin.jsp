<%-- 
    Document   : admin
    Created on : 2013-7-4, 20:36:15
    Author     : Yecfly
--%>
<jsp:useBean id="pore" class="model.managers.PR" scope="page" >
    <jsp:setProperty name="pore" property="db" value="${accountManager}" />
</jsp:useBean>
<center>
    <table><tr><td><h3>Welcome to admin page, ${sessionScope.Aaccount.name}!</h3></td>
            <td><form name="logout" action="/phtrs/loginhandler" method = "post">
                    <input type="submit" name="logout" value="logout"/>
                </form></td></tr>

        <tr><td><br/></td></tr>
        <tr><td><br/></td></tr>      
        <form name="find" action="/phtrs/admin_search" method="post">
            <tr><td><p>Timestam:<input type="text" name="date" size="30" maxlength="30"/></p>
                </td><td><input type="submit" name="search" value="search"/></td>
            </tr>
        </form> 
        <tr><td><br/></td></tr>



        <tr><td><p>List&nbsp;&nbsp;<a href="/phtrs/admin_list" title="unrepaired">UR</a>&nbsp;&nbsp;<a href="/phtrs/admin_list_w" title="work in progress">WIP</a>
                    &nbsp;&nbsp;<a href="/phtrs/admin_list_r" title="repaired">RE</a>&nbsp;&nbsp;<a href="/phtrs/admin_list_t" title="temporary repaired">TR</a>&nbsp;&nbsp;potholes</p></td></tr>
        <c:if test="${!empty sessionScope.records}">
            <c:if test="${!empty sessionScope.recordsyes}">
                <tr>
                    <td>Timestamp</td><td>Ditrict</td><td>Repair Priority</td><td>Status</td>
                </tr>
                <c:forEach var="p" begin="0" items="${pore.un}">
                    <tr>
                        <c:set var="pid" value="${p.id}"/>
                        <td bgcolor="#ffffaa">
                            <a href="/phtrs/admin_detail?id=${pid}">${pid}&nbsp;&nbsp;</a>
                        </td>
                        <td>
                            <p>${p.district}&nbsp;&nbsp;</p>
                        </td>
                        <td>
                            <p>${p.repri}&nbsp;&nbsp;</p>
                        </td>
                        <td>
                            <p>${p.status}</p>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${!empty sessionScope.recordsno}">
                <tr><td><p>No pothole records found</p></td></tr>
            </c:if>
        </c:if>



        <c:if test="${!empty sessionScope.detail}">
            <c:set var="rec" value="${pore.po}"/>
            <tr><td><br/></td></tr><tr><td><br/></td></tr>
            <tr><td><p>Record detail</p></td></tr>
            <tr>
                <td><p>Timestamp:${rec.id}&nbsp;&nbsp;</p></td>
                <td><p>Size:${rec.psize}</p></td>
            </tr>
            <tr>
                <td><p>Street Address:${rec.streetaddr}&nbsp;&nbsp;</p></td>
                <td><p>Location:${rec.location}</p></td>
            </tr>
            <tr>
                <td><p>District:${rec.district}&nbsp;&nbsp;</p></td>
                <td><p>Repaire Priority:${rec.repri}</p></td>
            </tr>
            <tr>
                <td><p>Reporter's Name:${rec.cname}&nbsp;&nbsp;</p></td><td><p>Reporter's Phone:${rec.cphone}</p></td>
            </tr>
            <tr><td><p>Reporter's Address:${rec.caddr}</p></td></tr>
            <form name="update" action="/phtrs/admin_update" method="post">
                <input name="id" type="hidden" value="${rec.id}"/>
                <tr><td><p>CrewID:<input name="crewid" type="number" value="${rec.crewid}" size="8"/>&nbsp;&nbsp;</p></td>
                    <td><p>Crew Head Count:<input name="hc" type="number" value="${rec.hc}" size="8"</p></td></tr>
                <tr><td><p>Work Hour:<input name="workhour" type="number" value="${rec.workhour}" size="8"/>&nbsp;&nbsp;</p></td>
                    <td><p>Equipment Assign:<select name="ea" >
                                <option value="${rec.ea}">${rec.ea}</option>
                                <option value="Standard A package">Standard A</option>
                                <option value="Standard B package">Standard B</option>
                                <option value="Standard C package">Standard C</option>
                            </select>&nbsp;&nbsp;</p></td>
                    <td><p>Status:<select name="status">
                                <option value="${rec.status}">${rec.status}</option>
                                <option value="work in progress">work in progress</option>
                                <option value="repaired">repaired</option>
                                <option value="temporary repaired">temporary repaired</option>
                                <option value="not repaired">not repaired</option>
                            </select></p></td>
                </tr>
                <tr><td><p>Filler Material used:<input name="material" type="number" value="${rec.material}"size="8"/></p></td></tr>
                <tr><td><p>Cost:<input name="cost" type="number" value="${rec.cost}" size="8"/>&nbsp;&nbsp;</p></td>
                    <td><p>Type:<input name="type" type="text" value="${rec.type}" size="20"/>&nbsp;&nbsp;</p></td>
                    <td><p>Damage Cost:<input name="dc" type="number" value="${rec.dc}" size="8"/></p></td></tr>
                <tr><td><input name="us" type="submit" value="update record"></td></tr>
            </form>
        </c:if>
        <c:if test="${!empty sessionScope.updateyes}">
            <tr><td><br/></td></tr>
            <tr><td>Update successfully!</td></tr>
        </c:if>
        <c:if test="${!empty sessionScope.updateno}">
            <tr><td><br/></td></tr>
            <tr><td>Fail in update.</td></tr>
        </c:if>
        <c:if test="${!empty sessionScope.nullvalue}">
            <tr><td><br/></td></tr>
            <tr><td>Encouter null value.</td></tr>
        </c:if>
    </table>
</center>
