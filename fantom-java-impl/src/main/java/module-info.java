/**
 * @author VISTALL
 * @since 31/05/2023
 */
module consulo.fantom.java.impl
{
	requires consulo.ide.api;
	requires consulo.fantom.api;

	requires consulo.java.language.api;
	requires consulo.java.execution.api;
	requires consulo.java.debugger.api;
	requires consulo.java.debugger.impl;
	requires consulo.internal.jdi;

	// TODO drop in future
	requires java.desktop;
}