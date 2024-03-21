import { useState } from "react";
import "./styles/FormScenario.css";

function Task() {
  const [hobbyValue, setHobbyValue] = useState("");
  const [typeValue, setTypeValue] = useState("");
  const [additionalValue, setAdditionalValue] = useState("");

  const handleHobbyChange = (e: any) => {
    setHobbyValue(e.target.value);
  };

  const handleTypeChange = (e: any) => {
    setTypeValue(e.target.value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:", hobbyValue + typeValue + additionalValue);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="task-tile">
        <label className="bigText">
          Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w
          przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez
          nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć
          wieków później zaczął być _______ przemyśle elektronicznym, pozostając
          praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz
          z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a
          ostatnio z ______ różne wersje Lorem Ipsum oprogramowaniem
          przeznaczonym do realizacji druków na komputerach osobistych, jak
          Aldus PageMaker
          <input
            type="text"
            value={hobbyValue}
            onChange={handleHobbyChange}
            className="input-text"
            placeholder="answer 1"
          />
        </label>
        <label className="bigText">
          <input
            type="text"
            value={typeValue}
            onChange={handleTypeChange}
            className="input-text"
            placeholder="answer 2"
          />
        </label>
      </div>
    </form>
  );
}

export default Task;
