<%@ page import="java.util.List" %>
<%@ page import="com.campusconnect.model.Item" %>

<%
    List<Item> items = (List<Item>) request.getAttribute("items");

    if (items == null) {
        response.sendRedirect("items");
        return;
    }

    String claimed = request.getParameter("claimed");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>CampusConnect - Browse Items</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:wght@700;800&family=Inter:wght@400;600;700&display=swap"
        rel="stylesheet">

    <link rel="stylesheet" href="assets/css/style.css">

</head>


<body>


<!-- ================= NAVIGATION ================= -->

<nav class="cc-nav">

    <span class="brand">

        CampusConnect

        <span class="tag">
            Lost &amp; Found HQ
        </span>

    </span>


    <div>

        <a href="dashboard.jsp">
            Dashboard
        </a>

        <a href="items">
            Browse Items
        </a>

        <a href="add-item.jsp">
            Post Item
        </a>

        <a href="logout">
            Logout
        </a>

    </div>

</nav>



<!-- ================= MAIN CONTENT ================= -->

<div class="cc-content">

    <div class="cc-card">


        <span class="eyebrow">
            Campus Lost &amp; Found
        </span>


        <h2 style="margin-top:8px;">
            Browse Items
        </h2>


        <p style="color:var(--slate);">

            Search for lost and found items posted by students.

        </p>



        <!-- ================= SUCCESS / ERROR MESSAGE ================= -->

        <% if ("true".equals(claimed)) { %>

            <div class="alert alert-success"
                 style="margin-top:20px;">

                Item claimed successfully!

            </div>

        <% } else if ("false".equals(claimed)) { %>

            <div class="alert alert-error"
                 style="margin-top:20px;">

                Failed to claim the item.
                Please try again.

            </div>

        <% } %>



        <!-- ================= SEARCH ================= -->

        <form
            action="items"
            method="get"
            style="margin-top:25px;">


            <!-- SEARCH KEYWORD -->

            <div class="field">

                <label for="keyword">
                    Search
                </label>


                <input
                    type="text"
                    id="keyword"
                    name="keyword"
                    placeholder="Search item, category, location..."
                    value="<%= request.getParameter("keyword") != null
                            ? request.getParameter("keyword")
                            : "" %>"
                >

            </div>



            <!-- ITEM TYPE -->

            <div class="field">

                <label for="type">
                    Item Type
                </label>


                <select
                    id="type"
                    name="type">


                    <option value="">
                        All Items
                    </option>


                    <option
                        value="LOST"
                        <%= "LOST".equals(request.getParameter("type"))
                            ? "selected"
                            : "" %>>

                        Lost

                    </option>


                    <option
                        value="FOUND"
                        <%= "FOUND".equals(request.getParameter("type"))
                            ? "selected"
                            : "" %>>

                        Found

                    </option>


                </select>

            </div>



            <!-- SEARCH BUTTON -->

            <button
                type="submit"
                class="btn btn-primary"
                style="margin-top:10px;">

                Search

            </button>



            <!-- CLEAR BUTTON -->

            <a
                href="items"
                class="btn btn-secondary"
                style="margin-top:10px;">

                Clear

            </a>


        </form>



        <!-- ================= ITEMS ================= -->

        <div style="margin-top:35px;">


            <% if (items.isEmpty()) { %>


                <!-- NO ITEMS -->

                <div class="alert alert-error">

                    No items found.

                </div>


            <% } else { %>


                <!-- LOOP THROUGH ITEMS -->

                <% for (Item item : items) { %>


                    <div
                        class="cc-card"
                        style="margin-top:20px;">



                        <!-- ================= ITEM HEADER ================= -->

                        <div
                            style="
                                display:flex;
                                justify-content:space-between;
                                align-items:center;
                                gap:15px;
                            ">


                            <div>


                                <h3 style="margin:0;">

                                    <%= item.getItemName() %>

                                </h3>


                                <p
                                    style="
                                        color:var(--slate);
                                        margin-top:6px;
                                    ">

                                    <%= item.getCategory() %>

                                </p>


                            </div>



                            <!-- LOST / FOUND -->

                            <span
                                style="
                                    font-weight:700;
                                ">

                                <%= item.getType() %>

                            </span>


                        </div>



                        <!-- ================= DESCRIPTION ================= -->

                        <p
                            style="
                                margin-top:15px;
                                color:var(--slate);
                            ">

                            <%= item.getDescription() %>

                        </p>



                        <!-- ================= LOCATION ================= -->

                        <p>

                            <strong>
                                Location:
                            </strong>

                            <%= item.getLocation() %>

                        </p>



                        <!-- ================= DATE ================= -->

                        <p>

                            <strong>
                                Date:
                            </strong>

                            <%= item.getItemDate() %>

                        </p>



                        <!-- ================= STATUS ================= -->

                        <p>

                            <strong>
                                Status:
                            </strong>

                            <%= item.getStatus() %>

                        </p>



                        <!-- ================= CLAIM BUTTON ================= -->

                        <% if ("OPEN".equalsIgnoreCase(item.getStatus())) { %>


                            <form
                                action="claim"
                                method="post"
                                style="margin-top:20px;">


                                <!-- ITEM ID -->

                                <input
                                    type="hidden"
                                    name="itemId"
                                    value="<%= item.getId() %>"
                                >



                                <!-- CLAIM MESSAGE -->

                                <div class="field">

                                    <label
                                        for="message_<%= item.getId() %>">

                                        Message

                                    </label>


                                    <textarea
                                        id="message_<%= item.getId() %>"
                                        name="message"
                                        rows="3"
                                        placeholder="Why are you claiming this item?"
                                    ></textarea>


                                </div>



                                <!-- CLAIM BUTTON -->

                                <button
                                    type="submit"
                                    class="btn btn-primary">

                                    Claim Item

                                </button>


                            </form>


                        <% } else { %>


                            <!-- ITEM ALREADY CLAIMED -->

                            <button
                                type="button"
                                class="btn btn-secondary"
                                disabled
                                style="margin-top:15px;">

                                Already Claimed

                            </button>


                        <% } %>


                    </div>


                <% } %>


            <% } %>


        </div>


    </div>

</div>


</body>

</html>