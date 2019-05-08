package com.massivecraft.massivecore.predicate;

import com.massivecraft.massivecore.xlib.guava.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

public class PredicateAnd<T> implements Predicate<T>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	@SafeVarargs
	public static <T> PredicateAnd<T> get(Predicate<? super T>... predicates) { return new PredicateAnd<>(predicates); }
	public static <T> PredicateAnd<T> get(Collection<Predicate<? super T>> predicates) { return new PredicateAnd<>(predicates); }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	@SafeVarargs
	public PredicateAnd(Predicate<? super T>... predicates)
	{
		this(ImmutableList.copyOf(predicates));
	}
	
	public PredicateAnd(Collection<Predicate<? super T>> predicates)
	{
		this.predicates = ImmutableList.copyOf(predicates);
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final List<Predicate<? super T>> predicates;
	public List<Predicate<? super T>> getPredicates() { return this.predicates; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(T type)
	{
		for (Predicate<? super T> predicate : this.getPredicates())
		{
			if ( ! predicate.apply(type)) return false;
		}
		return true;
	}
	
}
