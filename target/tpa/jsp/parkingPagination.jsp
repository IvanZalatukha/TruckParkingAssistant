<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Parkings</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination.css">
</head>

<body>


<main class="m-3" style="background-color: #ffffff">

    <div class="row col-md-6">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>Name</th>
                <th>Spots total</th>
                <th>Coordinate Latitude</th>
                <th>Coordinate Longitude</th>
                <th>Show</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="parking" items="${parkings}">
                <tr>
                    <td>${parking.getName()}</td>
                    <td>${parking.getSpotsTotal()}</td>
                    <td>${parking.getCoordinateLatitude()}</td>
                    <td>${parking.getCoordinateLongitude()}</td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="adminMap">
                            <input type="hidden" name="id" value="${parking.getId()}">
                            <input type="submit" class="page-link" name="submit" value="Show">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="goToAdiminPage">
                            <input type="submit" class="page-link" name="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="goToAdiminPage">
                            <input type="submit" class="page-link" name="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation for parkings">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="goToAdiminPage">
                        <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                        <input type="hidden" name="currentPage" value="${currentPage-1}">
                        <input type="submit" class="page-link" name="submit" value="Previous">
                    </form>

                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="goToAdiminPage">
                                <input type="hidden" name="currentPage" value="${i}">
                                <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                                <input type="submit" class="page-link" name="submit" value="${i}">
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="goToAdiminPage">
                        <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                        <input type="hidden" name="currentPage" value="${currentPage+1}">
                        <input type="submit" class="page-link" name="submit" value="Next">
                    </form>

                </li>
            </c:if>
        </ul>
    </nav>

</main>

</body>
</html>