// Add this line at the top of your component file
"use client";

// pages/create-table.tsx
import React, { ChangeEvent, useState } from "react";
import axios from "axios";

interface Column {
  columnName: string;
  columnType: string;
}

const CreateTable = () => {
  const [tableName, setTableName] = useState("");
  const [columns, setColumns] = useState<Column[]>([{ columnName: "", columnType: "" }]);

  const handleColumnChange = (index: number, event: ChangeEvent<HTMLInputElement>) => {
    const values = [...columns];
    values[index][event.target.name as keyof Column] = event.target.value;
    setColumns(values);
  };

  const handleAddColumn = () => {
    setColumns([...columns, { columnName: "", columnType: "" }]);
  };

  const handleRemoveColumn = (index: number) => {
    const values = [...columns];
    values.splice(index, 1);
    setColumns(values);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const requestBody = {
      tableName,
      columns,
    };

    try {
      const response = await axios.post(
          "http://localhost:8011/api/tables",
          requestBody,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
      );
      alert("Table created successfully!");
    } catch (error) {
      alert("Error creating table!");
    }
  };

  return (
      <div className="max-w-3xl mx-auto mt-10 p-8 border rounded-lg shadow-xl bg-white">
        <h1 className="text-3xl font-semibold text-center mb-6 text-gray-800">Create Table</h1>
        <form onSubmit={handleSubmit}>
          {/* Table Name Input */}
          <div className="mb-6">
            <label htmlFor="tableName" className="block text-lg font-medium text-gray-700 mb-2">
              Table Name
            </label>
            <input
                type="text"
                id="tableName"
                value={tableName}
                onChange={(e) => setTableName(e.target.value)}
                className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Enter table name"
                required
            />
          </div>

          {/* Columns Section */}
          <div className="mb-6">
            <label className="block text-lg font-medium text-gray-700 mb-2">Columns</label>
            {columns.map((column, index) => (
                <div key={index} className="flex items-center gap-4 mb-4">
                  <input
                      type="text"
                      name="columnName"
                      value={column.columnName}
                      onChange={(e) => handleColumnChange(index, e)}
                      placeholder="Column Name"
                      className="w-1/2 p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                      required
                  />
                  <input
                      type="text"
                      name="columnType"
                      value={column.columnType}
                      onChange={(e) => handleColumnChange(index, e)}
                      placeholder="Column Type"
                      className="w-1/2 p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                      required
                  />
                  <button
                      type="button"
                      onClick={() => handleRemoveColumn(index)}
                      className="text-red-500 hover:text-red-700"
                  >
                    Remove
                  </button>
                </div>
            ))}
            <button
                type="button"
                onClick={handleAddColumn}
                className="text-blue-500 hover:text-blue-700 mt-2"
            >
              + Add Column
            </button>
          </div>

          {/* Submit Button */}
          <button
              type="submit"
              className="w-full bg-blue-600 text-white py-3 rounded-md text-lg hover:bg-blue-700 transition duration-300"
          >
            Create Table
          </button>
        </form>
      </div>
  );
};

export default CreateTable;
