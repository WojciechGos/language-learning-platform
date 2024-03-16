import React from "react";
import Navigation from "../components/Navigation";
import MessageBoard from "../components/MessageBoard";
import GroupTile from "../components/GroupTile";

const Classes: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Lekcje</h1>
      <div style={{ display: "flex", flexDirection: "row" }}>
        <MessageBoard />
        <GroupTile name="Twój poziom" level={"B1"} />
      </div>
    </div>
  );
};

export default Classes;
