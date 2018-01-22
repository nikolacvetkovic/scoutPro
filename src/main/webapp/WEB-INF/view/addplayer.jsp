<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="partials/header.jsp" %>

		<div id="addplayer" class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6" style="background-color: rgba(61, 201, 179, 1);; margin-top: 50px; border-radius: 20px;">
				<form id="form">
					<div id="tmInput" class="form-group row">
						<label class="col-lg-2 col-form-label">Transfermarkt Url</label>
						<div class="col-lg-10">
							<input id="transfermarktUrl" class="form-control" type="text" name="transfermarktUrl">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-2 col-form-label">WhoScored Url</label>
						<div class="col-lg-10">
							<input id="whoScoredUrl" class="form-control" type="text" name="whoScoredUrl">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-2 col-form-label">PesDB Url</label>
						<div class="col-lg-10">
							<input id="pesDbUrl" class="form-control" type="text" name="pesDbUrl">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-2 col-form-label">Psml Url</label>
						<div class="col-lg-10">
							<input id="psmlUrl" class="form-control" type="text" name="psmlUrl">
						</div>
					</div>
					<div class="form-group row">
					    <label class="col-lg-2 col-form-label">My Player</label>
					    <div class="col-lg-10">
					    	<div class="form-check">
					    		<input id="myPlayer" type="checkbox" name="myPlayer">
				    		</div>
						</div>  
				  	</div>
				</form>
				<div class="form-group row">
						<div class="col-lg-10"></div>
						<div class="col-lg-2">
							<button class="btn btn-light btn-lg btn-block">Unesi</button>
						</div>
					</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
        <script src="/js/addplayer.js" type="text/javascript"></script>
<%@include file="partials/footer.jsp" %>