import { PieChart, Pie, Cell } from "recharts";

const data = [
  { name: "Grupa A", value: 400 },
  { name: "Grupa B", value: 300 },
  { name: "Grupa C", value: 300 },
  { name: "Grupa D", value: 200 },
];

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const PieCharts = () => (
  <PieChart width={400} height={400}>
    <Pie
      data={data}
      cx={200}
      cy={200}
      labelLine={false}
      outerRadius={80}
      fill="#8884d8"
      dataKey="value"
    >
      {data.map((entry, index) => (
        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
      ))}
    </Pie>
  </PieChart>
);

export default PieCharts;
