import React from "react";
import "./styles/GroupTile.css";

interface GroupTileProps {
  name: string;
  level: string;
}

const GroupTile: React.FC<GroupTileProps> = ({ name, level }) => {
  return (
    <div className="group-tile">
      <h2>{name}</h2>
      <div className="group-level">
        <text className="bigText">{level}</text>
      </div>
    </div>
  );
};

export default GroupTile;
