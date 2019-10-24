<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upTest</title>
</head>
<body>
upTest|<a href="admin.html">adminTest</a>|<a href="photographer.html">pgTest</a>
<form action="user/requestMessageCode" method="post">
    <input type="text" name="phone">
    <input type="submit" value=" ">
</form>
<hr/>
<form action="user/register" method="post">
    phone<input type="text" name="phone"> <br/>
    password<input type="password" name="password"> <br/>
    code <input type="text" name="message_code"><br/>
    <input type="submit" value=" ">
</form>
<hr/>
<form action="user/login" method="post">
    phone <input type="text" name="phone"> <br/>
    password<input type="text" name="password"> <br/>
    <input type="submit" value="login">
</form>
<hr/>
<form action="user/lookInfo" method="post">
    <input type="submit" value="profile">
</form>
<hr/>
changePassword
<form action="user/updatePassword" method="post">
    oldPW <input type="text" name="password"> <br/>
    newPW<input type="text" name="new_password"> <br/>
    <input type="submit" value="change">
</form>
<hr/>
passwordRset
<form action="user/resetPassword" method="post">
    phone<input type="text" name="phone"> <br/>
    newPW<input type="text" name="new_password"> <br/>
    code <input type="text" name="message_code"><br/>
    <input type="submit" value="rset">
</form>
<hr/>
editProfile
<form action="user/updateInfo" method="post">
    nickname <input type="text" name="nickname"> <br/>
    sex<input type="text" name="sex"> <br/>
    birthday <input type="text" name="birthday" value="2018-06-20"> <br/>
    school <input type="text" name="school"> <br/>
    <input type="submit" value="submit">
</form>
<hr/>
setHeadImage
<form action="user/setHeadImage" enctype="multipart/form-data" method="post">
    <input type="file" name="file">
    <input type="submit" value="upload">
</form>
<hr/>
listStyles
<form action="user/listAllStyles">
    <input type="submit" value="check">
</form>
<hr/>
publishNote
<form action="user/releaseNote" enctype="multipart/form-data" method="post">
    styleID <input type="text" name="style_id">
    content<input type="text" name="content">
    <input type="file" name="file" multiple="multiple">
    <input type="submit" value="publish">
</form>
<hr/>
listNotes
<form action="user/listAllNotes" method="post">
    styleID <input type="text" name="style_id">
    <input type="submit" value="check">
</form>
<hr/>
listNotePage
<form action="user/listAllNotesWithPage" method="post">
    styleID <input type="text" name="style_id">
    pageSize <input type="text" name="page_size">
    currentPage<input type="text" name="current_page">
    <input type="submit" value="submit">
</form>
comment
<form action="user/commentNote" method="post">
    noteID <input type="text" name="note_id">
    content<input type="text" name="content">
    parentId<input type="text" name="father_id">
    <input type="submit" value="submit">
</form>
<hr/>
admire
<form action="user/admireNote" method="post">
    noteID <input type="text" name="note_id">
    <input type="submit" value="submit">
</form>
<hr/>
cancelAdmire
<form action="user/deleteAdmire" method="post">
    帖子ID <input type="text" name="note_id">
    <input type="submit" value="cancelAdmire">
</form>

<hr/>
deleteComment
<form action="user/deleteMyComment" method="post">
    commentID <input type="text" name="comment_id">
    <input type="submit" value="delete">
</form>
<form action="user/deleteOthersComment" method="post">
    commentID <input type="text" name="comment_id">
    <input type="submit" value="delete">
</form>
<hr/>
listNote
<form action="user/listMyNotes" method="post">
    <input type="submit" value="list">
</form>
<hr/>
delete
<form action="user/deleteMyNote" method="post">
    的帖子的ID <input type="text" name="note_id">
    <input type="submit" value="delete">
</form>
<hr/>
findNote
<form action="user/findNoteById" method="post">
    帖子ID <input type="text" name="note_id">
    <input type="submit" value="submit">
</form>
<hr/>
checkinfo
<form action="user/lookOthersInfo" method="post">
    用户ID <input type="text" name="user_id">
    <input type="submit" value="submit">
</form>
<hr/>
identity
<form action="user/queryMyIdentity" method="post">
    <input type="submit" value="check">
</form>
<hr/>
otherIdentity
<form action="user/queryOthersIdentity" method="post">
    用户ID<input type="text" name="user_id">
    <input type="submit" value="check">
</form>
<hr/>
applyPg
<form action="user/requestToBePhotographer" enctype="multipart/form-data" method="post">
    numID<input type="text" name="card_no">
    photo<input type="file" name="file">
    <input type="submit" value="confirm">
</form>
<hr/>
record
<form action="user/listVerifyRecord" enctype="multipart/form-data" method="post">
    <input type="submit" value="submit">
</form>
<hr/>
listAllPG
<form action="user/listPhotographersBySchool" method="post">
    school<input type="text" name="school" value="WSU">
    <input type="submit" value="list">
</form>
<hr/>
listPgWithPage
<form action="user/listPhotographersBySchoolWithPage" method="post">
    school<input type="text" name="school" value="WSU">
    pageSize<input type="text" name="page_size">
    currentPage<input type="text" name="current_page">
    <input type="submit" value="list">
</form>
<hr/>
findPgInfo
<form action="user/findPhotographerById" method="post">
    PgId<input type="text" name="photographer_id">
    <input type="submit" value="list">
</form>
<hr/>
appointmentPg
<form action="user/orderPhotographer" method="post">
    PgId<input type="text" name="photographer_id">
    address<input type="text" name="address">
    note<input type="text" name="other">
    <input type="submit" value="apply">
</form>
<hr/>
cancelAppointment
<form action="user/cancelOrder" method="post">
    id<input type="text" name="orderinfo_id">
    <input type="submit" value="cancle">
</form>
<hr/>
listApp
<form action="user/listOrders" method="post">
    <input type="submit" value="list">
</form>
<hr/>
listAllAppWithPage
<form action="user/listOrdersWithPage" method="post">
    pageSize <input type="text" name="page_size">
    currentPage<input type="text" name="current_page">
    <input type="submit" value="list">
</form>
<hr/>
followPg
<form action="user/followPhotographer" method="post">
    PgId<input type="text" name="photographer_id">
    <input type="submit" value="follow">
</form>
<hr/>
cancelFollow
<form action="user/cancelFollow" method="post">
    PgId<input type="text" name="photographer_id">
    <input type="submit" value="cancel">
</form>
<hr/>
listFollows
<form action="user/listFollows" method="post">
    <input type="submit" value="list">
</form>
<hr/>
commentPg
<form action="user/reviewPhotographer" method="post">
    PgId<input type="text" name="photographer_id">
    content<input type="text" name="content">
    starValue<input type="text" name="change_star_value">
    <input type="submit" value="comment">
</form>
</body>
</html>
