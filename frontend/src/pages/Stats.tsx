import React from "react";
import Navigation from "../components/Navigation";
import StatsBigChart from "../components/StatsBigChart";
import GroupTile from "../components/GroupTile";

const Stats: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Statystyki</h1>
      <div style={{ display: "flex", flexDirection: "row" }}>
        <StatsBigChart />
        <GroupTile name="Level" level="11" />
      </div>
      <div style={{ display: "flex", flexDirection: "row" }}>
        <StatsBigChart />
        <GroupTile name="Progress" level="27%" />
      </div>
    </div>
  );
};

export default Stats;
