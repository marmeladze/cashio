def diophanite_solver_for(x)

  #
  # EQUATION: 1a + 5b + 10c + 50d = x
  #

  solvable=[]
  solvable_values=[] 

  while x>0
    for a in (0..x/1)
      for b in (0..x/5)
        for c in (0..x/10)
          for d in (0..x/50)
            total=1*a + 5*b + 10*c+50*d
            if total==x
              solvable<< x
              solvable_values<< [x, [a, b, c, d]] 
            end
          end          
        end
      end
    end
    x=x-1
  end
  return solvable_values
end

def pairs_for(x)
  diophanite_solver_for(x).select {|p| p.first == x }
end
