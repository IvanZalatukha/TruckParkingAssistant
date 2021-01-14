<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>

<main class="m-3">

    <h1>Show all Parkings</h1>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="goToPaginationPage">
        <input type="hidden" name="currentPage" value="1">

        <div class="form-group col-md-4">

            <label for="records">Select records per page:</label>

            <select class="form-control" id="records" name="recordsPerPage">
                <option value="5" selected>5</option>
                <option value="10">10</option>
                <option value="15">15</option>
            </select>

        </div>

        <button type="submit" class="btn btn-primary">Submit</button>

    </form>
</main>

</body>
</html>