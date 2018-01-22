<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ScoutPro</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="/webjars/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="/css/style.css" rel="stylesheet" type="text/css">
        <script src="/webjars/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(61, 201, 179, 1); height: 78px;">
            
            <a class="navbar-brand" href="/">
                <img src="/logo.png" width="50px" height="50px" class="d-inline-block">
                <span class="align-middle ml-1" style="font-size: 35px">ScoutPro</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar" aria-controls="myNavbar" aria-expanded="false" aria-label="Toggle">
                <!-- <i class="fa fa-bars"></i> -->
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="navbar-nav ml-auto mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/addplayer">Add Player</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Compare players</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Something</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto mr-auto ">
                    <li class="nav-item mr-2" style="width: 25em">
                        <input id="search" class="form-control" type="search" placeholder="Search players" autocomplete="off" name="s">
                    </li>
                </ul>
                <form class="form-inline my-2">
                    <button class="btn btn-outline-light my-sm-0" type="button" name="logout" >Logout</button>
                </form>
            </div>            
        </nav>
        <div class="container-fluid">
            <div class="alert alert-success mt-2 my-2 py-2" style="display: none;">
                
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
            </div>