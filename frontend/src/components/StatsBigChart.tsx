import React from "react";
import "./styles/StatsChart.css";
import PieCharts from "./PieCharts";
//import chart from "../assets/chart1.jpg";
// import chart2 from "../assets/chart2.png";

const StatsBigChart: React.FC = () => {
  return (
    <div className="stats-big-chart">
      <h2>Wykresy</h2>
      <div className="stats-text">
        <PieCharts />
        {/* <img src={chart} alt="Chart 1" style={{ maxHeight: "400px" }} /> */}
        {/* <img src={chart2} alt="Chart 1" style={{ maxHeight: "400px" }} /> */}
      </div>
    </div>
  );
};

export default StatsBigChart;
