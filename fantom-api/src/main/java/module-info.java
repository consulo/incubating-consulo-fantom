/**
 * @author VISTALL
 * @since 31/05/2023
 */
module consulo.fantom.api
{
	requires consulo.ide.api;

	exports consulo.fantom;
	exports consulo.fantom.api.icon;
	exports consulo.fantom.api.localize;
	exports consulo.fantom.module.extension;
	exports org.fandev.index;
	exports org.fandev.lang.fan;
	exports org.fandev.lang.fan.psi;
	exports org.fandev.lang.fan.psi.api.modifiers;
	exports org.fandev.lang.fan.psi.api.statements;
	exports org.fandev.lang.fan.psi.api.statements.blocks;
	exports org.fandev.lang.fan.psi.api.statements.expressions;
	exports org.fandev.lang.fan.psi.api.statements.params;
	exports org.fandev.lang.fan.psi.api.statements.typeDefs;
	exports org.fandev.lang.fan.psi.api.statements.typeDefs.members;
	exports org.fandev.lang.fan.psi.api.topLevel;
	exports org.fandev.lang.fan.psi.api.types;
	exports org.fandev.lang.fan.psi.impl;
	exports org.fandev.lang.fan.psi.stubs.index;
	exports org.fandev.runner;
	exports org.fandev.sdk;
	exports org.fandev.utils;
}