package jniosemu.emulator;

import jniosemu.instruction.emulator.Instruction;

public class ProgramLine
{
	private boolean breakpoint = false;
	private final int opCode;
	private final Instruction instruction;
	private final String sourceCodeLine;

	public ProgramLine(int opCode, Instruction instruction, String sourceCodeLine) {
		this.opCode = opCode;
		this.instruction = instruction;
		this.sourceCodeLine = sourceCodeLine;
	}

	public boolean getBreakPoint() {
		return this.breakpoint;
	}

	public String getOpCode() {
		if (this.opCode == 0)
			return null;

		String hexOpCode = "00000000"+ Integer.toHexString(this.opCode);
		return "0x"+ hexOpCode.substring(hexOpCode.length()-8, hexOpCode.length());
	}

	public String getInstruction() {
		if (this.instruction == null)
			return null;

		return this.instruction.toString();
	}

	public String getSourceCodeLine() {
		if (this.sourceCodeLine == null)
			return null;

		return this.sourceCodeLine;
	}

	public String toString() {
		String ret = "";
		if (this.getSourceCodeLine() != null)
			ret += this.getSourceCodeLine() +"\n";
		if (this.getOpCode() != null)
			ret += this.getOpCode() +" "+ this.getInstruction();
		return ret;
	}
}