/**
 * @author VISTALL
 * @since 31/05/2023
 */
open module consulo.fantom
{
	requires consulo.ide.api;
	requires consulo.fantom.api;

	exports consulo.fantom.impl.highlighter;
	exports consulo.fantom.impl.language.psi;
	exports org.fandev.impl;
	exports org.fandev.impl.actions.execution;
	exports org.fandev.impl.actions.generation;
	exports org.fandev.impl.compiler;
	exports org.fandev.impl.findUsages;
	exports org.fandev.impl.icons;
	exports org.fandev.impl.idea.gotoclass;
	exports org.fandev.impl.lang.fan;
	exports org.fandev.impl.lang.fan.editor;
	exports org.fandev.impl.lang.fan.highlighting;
	exports org.fandev.impl.lang.fan.parameterInfo;
	exports org.fandev.impl.lang.fan.parser;
	exports org.fandev.impl.lang.fan.parsing;
	exports org.fandev.impl.lang.fan.parsing.auxiliary;
	exports org.fandev.impl.lang.fan.parsing.auxiliary.facets;
	exports org.fandev.impl.lang.fan.parsing.auxiliary.modifiers;
	exports org.fandev.impl.lang.fan.parsing.expression;
	exports org.fandev.impl.lang.fan.parsing.expression.argument;
	exports org.fandev.impl.lang.fan.parsing.expression.arithmetic;
	exports org.fandev.impl.lang.fan.parsing.expression.logical;
	exports org.fandev.impl.lang.fan.parsing.statements;
	exports org.fandev.impl.lang.fan.parsing.statements.declaration;
	exports org.fandev.impl.lang.fan.parsing.statements.expressions.arguments;
	exports org.fandev.impl.lang.fan.parsing.statements.typeDefinitions;
	exports org.fandev.impl.lang.fan.parsing.statements.typeDefinitions.blocks;
	exports org.fandev.impl.lang.fan.parsing.statements.typeDefinitions.members;
	exports org.fandev.impl.lang.fan.parsing.statements.typeDefinitions.typeDefs;
	exports org.fandev.impl.lang.fan.parsing.topLevel;
	exports org.fandev.impl.lang.fan.parsing.types;
	exports org.fandev.impl.lang.fan.parsing.util;
	exports org.fandev.impl.lang.fan.psi;
	exports org.fandev.impl.lang.fan.psi.api;
	exports org.fandev.impl.lang.fan.psi.api.modifiers;
	exports org.fandev.impl.lang.fan.psi.api.statements.arguments;
	exports org.fandev.impl.lang.fan.psi.api.statements.expressions;
	exports org.fandev.impl.lang.fan.psi.api.statements.typeDefs;
	exports org.fandev.impl.lang.fan.psi.api.statements.typeDefs.members;
	exports org.fandev.impl.lang.fan.psi.impl;
	exports org.fandev.impl.lang.fan.psi.impl.modifiers;
	exports org.fandev.impl.lang.fan.psi.impl.statements;
	exports org.fandev.impl.lang.fan.psi.impl.statements.arguments;
	exports org.fandev.impl.lang.fan.psi.impl.statements.blocks;
	exports org.fandev.impl.lang.fan.psi.impl.statements.expressions;
	exports org.fandev.impl.lang.fan.psi.impl.statements.params;
	exports org.fandev.impl.lang.fan.psi.impl.statements.typedefs;
	exports org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;
	exports org.fandev.impl.lang.fan.psi.impl.types;
	exports org.fandev.impl.lang.fan.psi.stubs;
	exports org.fandev.impl.lang.fan.psi.stubs.elements;
	exports org.fandev.impl.lang.fan.psi.stubs.impl;
	exports org.fandev.impl.lang.fan.psi.stubs.index;
	exports org.fandev.impl.lang.fan.resolve;
	exports org.fandev.impl.lang.fan.structure;
	exports org.fandev.impl.lang.fan.structure.elements;
	exports org.fandev.impl.lang.fan.structure.elements.impl;
	exports org.fandev.impl.lang.fan.structure.elements.itemsPresentations;
	exports org.fandev.impl.lang.fan.structure.elements.itemsPresentations.impl;
	exports org.fandev.impl.lang.fan.types;
	exports org.fandev.impl.utils;
}