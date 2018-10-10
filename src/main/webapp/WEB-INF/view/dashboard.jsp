<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="partials/header.jsp" %>

        <div class="row">
            <div id="left" class="col-lg-6 my-2" style="overflow: scroll;overflow-x: hidden; padding-left: 0; padding-right: 0;">
                <div class="text-center">
                    <button id="allPlayersButton" class="btn" style="background-color: rgba(61, 201, 179, 1); border-color: rgba(61, 201, 179, 1); color:rgba(255, 255, 255, 0.8);">All Players
                    </button>
                    <button id="myPlayersButton" class="btn" style="background-color: rgba(61, 201, 179, 1); border-color: rgba(61, 201, 179, 1); color: rgba(255, 255, 255, 0.8);">My Players
                    </button>
                    <button id="freePlayersButton" class="btn" style="background-color: rgba(61, 201, 179, 1); border-color: rgba(61, 201, 179, 1); color: rgba(255, 255, 255, 0.8);">Free Players
                    </button>
                </div>
                <table class="table table-hover table-scroll" style="margin-bottom: 0">
                    <thead>
                        <tr>
                            <th class="">Name</th>
                            <th class="" style="width: 10%">
                                Age
                                <span class="badge badge-primary">
                                    <i class="fa fa-sort-desc" aria-hidden="true"></i>
                                </span><span class="badge badge-primary">
                                    <i class="fa fa-sort-asc" aria-hidden="true"></i>
                                </span>
                            </th>
                            <th id="position" style="width: 14%">
                                Position
                                <span class="badge badge-primary">
                                    <i class="fa fa-sort-desc" aria-hidden="true"></i>
                                </span><span class="badge badge-primary">
                                    <i class="fa fa-sort-asc" aria-hidden="true"></i>
                                </span>
                            </th>
                            <th class="" style="width: 13%">
                                Overall
                                <span class="badge badge-primary">
                                    <i class="fa fa-sort-desc" aria-hidden="true"></i>
                                </span><span class="badge badge-primary">
                                    <i class="fa fa-sort-asc" aria-hidden="true"></i>
                                </span>
                            </th>
                            <th class="">
                                Psml Value
                                <span class="badge badge-primary">
                                    <i class="fa fa-sort-desc" aria-hidden="true"></i>
                                </span><span class="badge badge-primary">
                                    <i class="fa fa-sort-asc" aria-hidden="true"></i>
                                </span>
                            </th>
                            <th class="">
                                TM Value
                                <span class="badge badge-primary">
                                    <i class="fa fa-sort-desc" aria-hidden="true"></i>
                                </span><span class="badge badge-primary">
                                    <i class="fa fa-sort-asc" aria-hidden="true"></i>
                                </span>
                            </th>
                        </tr>
                    </thead>
                    
                    <tbody id="tab-body"></tbody>
                </table>
            </div>
            <div id="right-side" class="col-lg-6 my-2">
                <div id="right" style="overflow: scroll;overflow-x: hidden;">
                <div class="row" style="width: 99%;">
                    <div id="tm" class="col-lg-6" style="border-right-style: solid; border-width: 2px;">
                        <h6>Transfermarkt Info <span id="lastMeasured" style="font-size: 12px;"></span> <span class="badge badge-primary" data-url="#">Measure Now!</span></h6>
                        <hr style="width: 55%;">
                        <div class="row">
                            <div class="col-sm-12 col-lg-12">
                                <table class="table table-hover">
                                    <tbody>
                                        <tr>
                                            <td style="width: 50%">National team</td>
                                            <td id="nationalTeam"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Club team</td>
                                            <td id="clubTeam"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Contract until</td>
                                            <td id="contractUntil"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Current value</td>
                                            <td id="currentValue"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Last changed</td>
                                            <td id="lastChangedCurrentValue"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div id="psml" class="col-lg-6">
                        <h6>Psml Info <span id="lastMeasured" style="font-size: 12px;"></span> <span class="badge badge-primary" data-url="#">Measure Now!</span></h6>
                        <hr style="width: 40%;">
                        <div class="row">
                            <div class="col-sm-12 col-lg-12">
                                <table class="table table-hover">
                                    <tbody>
                                        <tr>
                                            <td style="width: 50%">Club team</td>
                                            <td id="clubTeam"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Psml value</td>
                                            <td id="psmlValue"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="border-style: solid none; border-width: 2px;">
                    <div id="pesDb" class="col-lg-12">
                        <h6>PesDb Info <span id="lastMeasured" style="font-size: 12px;"></span> <span class="badge badge-primary" data-url="#">Measure Now!</span></h6>
                        <hr style="width: 20%;">
                        <div class="row" style="">
                            <div class="col-sm-4 col-lg-4" style="padding: 0">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td style="width: 50%">Name:</td>
                                            <td id="pesDbName"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%" >Team:</td>
                                            <td id="teamName"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Foot:</td>
                                            <td id="foot"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Condition:</td>
                                            <td id="weekCondition"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Primary position:</td>
                                            <td id="primaryPosition"></td>
                                        </tr>
                                        <tr>
                                            <td>Other position:</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Strong</td>
                                            <td id="otherStrongPositions"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 50%">Weak</td>
                                            <td id="otherWeakPositions"></td>
                                        </tr>
                                    </tbody>
                                </table>                       
                            </div>
                            <div class="col-sm-4 col-lg-4">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td style="width: 70%;">Attacking Prowess:</td>
                                            <td id="attackingProwess"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Ball Control:</td>
                                            <td id="ballControl"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Dribbling:</td>
                                            <td id="dribbling"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Low Pass:</td>
                                            <td id="lowPass"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Lofted Pass:</td>
                                            <td id="loftedPass"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Finishing:</td>
                                            <td id="finishing"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Place Kicking:</td>
                                            <td id="placeKicking"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Swerve:</td>
                                            <td id="swerve"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Header:</td>
                                            <td id="header"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Defensive Prowess:</td>
                                            <td id="defensiveProwess"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Ball Winning:</td>
                                            <td id="ballWinning"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Kicking Power:</td>
                                            <td id="kickingPower"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Speed:</td>
                                            <td id="speed"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Explosive Power:</td>
                                            <td id="explosivePower"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Body Control:</td>
                                            <td id="bodyControl"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-4 col-lg-4">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td style="width: 70%;">Physical Contact:</td>
                                            <td id="physicalContact"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Jump:</td>
                                            <td id="jump"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Stamina:</td>
                                            <td id="stamina"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Goalkeeping:</td>
                                            <td id="goalkeeping"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Catching:</td>
                                            <td id="catching"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Clearing:</td>
                                            <td id="clearing"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Reflexes:</td>
                                            <td id="reflexes"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Coverage:</td>
                                            <td id="coverage"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Form:</td>
                                            <td id="form"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Injury Resistance:</td>
                                            <td id="injuryResistance"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Weak Foot Usage:</td>
                                            <td id="weakFootUsage"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Weak Foot Accuracy:</td>
                                            <td id="weakFootAccuracy"></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">&nbsp;</td>
                                            <td id=""></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">&nbsp;</td>
                                            <td id=""></td>
                                        </tr>
                                        <tr>
                                            <td style="width: 70%;">Overall Rating:</td>
                                            <td id="overallRating"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div id="ws" class="col-lg-12">
                        <h6>Whoscored Info <span id="lastMeasured" style="font-size: 12px;"></span> <span class="badge badge-primary" data-url="#">Measure Now!</span></h6>
                        <hr style="width: 25%;">
                        <div class="row">
                            <div class="col-sm-12 col-lg-12">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Competition</th>
                                            <th>Apps</th>
                                            <th>Mins</th>
                                            <th>Goals</th>
                                            <th>Assists</th>
                                            <th>Yel</th>
                                            <th>Red</th>
                                            <th>SpG</th>
                                            <th>PS%</th>
                                            <th>AerialsWon</th>
                                            <th>MotM</th>
                                            <th>Rating</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
        
        <div id="content">
            
        </div>
    </div>
    <script src="/js/dashboard.js" type="text/javascript"></script>
    <script type="text/javascript">
        $('#search').keyup(function(){
            $('#content').empty();
            if(this.value.length > 2){
                $.get('/player/search/' + this.value, function(data){
                    data.forEach(function(player){
                        var ol = document.createElement('ol');
                        var value = document.createTextNode(player.transfermarktInfo.playerName);
                        ol.appendChild(value);
                        $('#content').append(ol);
                    });
                });
            }
        });
    </script>
<%@include file="partials/footer.jsp" %>
