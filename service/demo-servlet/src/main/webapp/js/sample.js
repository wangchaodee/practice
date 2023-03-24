$(function () {

    $.ajax({
        type: "GET",
        url: "./ManageCities"
    }).done(function () {

        $("#jsGrid").jsGrid({
            height: "60%",
            width: "50%",
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            pageSize: 10,
            controller: {
                loadData: function (filter) {
                    return $.ajax({
                        type: "GET",
                        url: "./ManageCities",
                        data: filter
                    });
                },
                insertItem: function (item) {
                    return $.ajax({
                        type: "POST",
                        url: "./ManageCities",
                        data: item
                    });
                },
                updateItem: function (item) {
                    return $.ajax({
                        type: "PUT",
                        url: "./ManageCities",
                        data: item
                    });
                },
                deleteItem: function (item) {
                    return $.ajax({
                        type: "DELETE",
                        url: "./ManageCities",
                        data: item
                    });
                }
            },
            fields: [
                {name: "name", title: "Name", type: "text", width: 60},
                {name: "population", title: "Population", type: "text",  width: 50},
                {type: "control"}
            ]
        });

    });
});