import { useState } from "react";
import "./styles/FormScenario.css";

function FormScenario() {
  const [hobbyValue, setHobbyValue] = useState("");
  const [typeValue, setTypeValue] = useState("");
  const [additionalValue, setAdditionalValue] = useState("");

  const handleHobbyChange = (e: any) => {
    setHobbyValue(e.target.value);
  };

  const handleTypeChange = (e: any) => {
    setTypeValue(e.target.value);
  };

  const handleAdditionalChange = (e: any) => {
    setAdditionalValue(e.target.value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:", hobbyValue + typeValue + additionalValue);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-tile">
        <label className="bigText">
          Wpisz swoje hobby:
          <input
            type="text"
            value={hobbyValue}
            onChange={handleHobbyChange}
            className="input-text"
          />
        </label>
      </div>
      <div className="form-tile">
        <label className="bigText">
          Wybierz rodzaj:
          <input
            type="text"
            value={typeValue}
            onChange={handleTypeChange}
            className="input-text"
            placeholder="rozmowa / dokończenie historii / gra / zabawa"
          />
        </label>
      </div>
      <div className="form-tile">
        <label className="bigText">
          Dodatkowe info:
          <input
            type="text"
            value={additionalValue}
            onChange={handleAdditionalChange}
            className="input-text"
          />
        </label>
      </div>

      <button type="submit" className="button-send">
        GENERUJ!
      </button>
    </form>
  );
}

export default FormScenario;
