import React from "react";
import Navigation from "../components/Navigation";
import MessageBoard from "../components/MessageBoard";
import GroupTile from "../components/GroupTile";

const Activities: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Aktywności zajęciowe</h1>
      <div style={{ display: "flex", flexDirection: "row" }}>
        <MessageBoard
          name="Najnowsze aktywności"
          messages={[
            "Oglądanie meczu z komentarzem w innym języku",
            "Słówka piłkarskie",
            "Słynne mecze - opowieści w innym języku",
            "Tworzenie zasad obcojęzycznej gry karcianej z piłkarzami",
          ]}
        />
        <GroupTile name="Wybrana grupa" level={"piłka nożna"} />
      </div>
    </div>
  );
};

export default Activities;
