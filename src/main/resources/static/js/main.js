<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});

      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

      var data = new google.visualization.DataTable();
      data.addColumn('string', '장르');
      data.addColumn('number', '점유율');
      data.addRows([
        ['RTS', 45.52],
        ['FPS', 25.7],
        ['RPG', 16.4],
        ['스포츠', 5.89],
        ['액션', 4.58]
      ]);

      // Set chart options
      var options = {'title':'게임별 점유율',
                     'width':400,
                     'height':300};

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
    </script>