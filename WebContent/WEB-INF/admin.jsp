<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resource admin</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="admin.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="block">
            <h1>Resource admin</h1>
            <%@include file="../menu.jsp"%>
            <div class="admin-panel-catheader">Пользователь</div>
            <table border="0" align="center" class="admin-panel-table">
                <tr>
                    <td><a href="UserAdminController?page=add">
                            <div>
                                <img src="img/user-add-icon.png" />
                            </div>
                            <div>Добавить пользователя</div> </a></td>
                    <td><a href="UserAdminController?page=accept"><div>
                                <img src="img/user-accept-icon.png" />
                            </div>
                            <div>Принять пользователя</div> </a></td>
                    <td><a href="UserAdminController?page=edit"><div>
                                <img src="img/user-edit-icon.png" />
                            </div>
                            <div>Редактировать пользователя</div> </a></td>
                </tr>
                <tr>
                    <td><a href="UserAdminController?page=delete"><div>
                                <img src="img/user-delete-icon.png" />
                            </div>
                            <div>Удалить пользователя</div> </a></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
            <div class="admin-panel-catheader">Ресурс</div>
            <table border="0" align="center" class="admin-panel-table">
                <tr>
                    <td>
                        <a href="ResourceAdminController?page=add"">
                            <div>
                                <img src="img/resource-add-icon.png" />
                            </div>
                            <div>Добавить ресурс</div> 
                        </a>
                    </td>
                    <td>
                        <a href="ResourceAdminController?page=edit">
                            <div>
                                <img src="img/resource-add-icon.png" />
                            </div>
                            <div>Редактировать ресурс</div> 
                        </a>
                    </td>
                    <td>
                        <a href="ResourceAdminController?page=delete">
                            <div>
                                <img src="img/resource-delete-icon.png" />
                            </div>
                            <div>Удалить ресурс</div>
                        </a>
                    </td>
                </tr>
            </table>
            
        </div>
    </body>
</html>